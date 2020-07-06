package com.clearbox.stockfolio.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.clearbox.stockfolio.R;
import com.clearbox.stockfolio.adapter.DateAxisFormatter;
import com.clearbox.stockfolio.customview.NumberKeyboard;
import com.clearbox.stockfolio.network.model.FinnhubAssetCandleData;
import com.clearbox.stockfolio.network.model.FinnhubAssetStockData;
import com.clearbox.stockfolio.viewmodel.StockfolioViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddAssetDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAssetDetailFragment extends Fragment {

    public interface OnAddAssetDetailFragmentInteractionListener {
        void updateGraphAtInterval(int i);

        void startCoinGraphDataService();

        void goToPortfolioFragment();
    }

    private StockfolioViewModel mModel;

    private LineChart mChart;
    private boolean mNewGraph;
    private TextView mTextViewHigh, mBid, m24Volume, mTextViewLow, mAsk, m24Change, mTextViewStockPrice, mTextViewStockPriceDelta;
    private EditText mEditTextQuantity;

    private Button mButtonBuy, mButtonSell;

    private Spinner mSpinner;
    private NumberKeyboard mNumberKeyboard;

    private float mLow, mHigh;

    private OnAddAssetDetailFragmentInteractionListener mListener;

    public AddAssetDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddTransactionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAssetDetailFragment newInstance() {
        AddAssetDetailFragment fragment = new AddAssetDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_asset_detail, container, false);

        setupViews(view);

        setupData();

        return view;
    }

    private void setupData() {
        if (getActivity() != null)
            mModel = ViewModelProviders.of(getActivity()).get(StockfolioViewModel.class);

        if (mModel != null) {
            //Data for the graph
            mModel.getFinnhubAssetCandleData(2 ).observe(this, new Observer<FinnhubAssetCandleData>() {
                @Override
                public void onChanged(FinnhubAssetCandleData finnhubAssets) {
                    String status = finnhubAssets.status;
                    if (status != null && status.equalsIgnoreCase("ok")) {
                        updateGraph(finnhubAssets);
                    } else {
                        updateGraph(null);
                    }
                }
            });

            //Data for the current ask and bid
            mModel.getFinnhubStockData().observe(this, new Observer<FinnhubAssetStockData>() {
                @Override
                public void onChanged(FinnhubAssetStockData candleData) {
                    mTextViewStockPrice.setText(String.valueOf(candleData.current));
                    DecimalFormat format = new DecimalFormat("##.00");
                    Double percentChange = ((candleData.current - candleData.previousClose) / candleData.previousClose) * 100; //TODO: NEW API CALL FOR 24 HRS AGO Change
                    mTextViewStockPriceDelta.setText(format.format(percentChange)+"%");
                }
            });
        }
    }

    private void setupViews(View view) {
        //Set-up graph
        mChart = (LineChart) view.findViewById(R.id.chart);
        mChart.setDescription(null);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new DateAxisFormatter(2));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        mEditTextQuantity = view.findViewById(R.id.EditText_Quantity);
//        showKeyboardForEditTextQuantity();

        mTextViewStockPrice = view.findViewById(R.id.TextView_stock_price);
        mTextViewStockPriceDelta = view.findViewById(R.id.TextView_stock_price_delta);

        mTextViewHigh = view.findViewById(R.id.TextView_high_price);
        mBid = view.findViewById(R.id.TextView_current_bid_price);
        m24Volume = view.findViewById(R.id.TextView_24_hour_volume);

        mTextViewLow = view.findViewById(R.id.TextView_low_price);
        mAsk = view.findViewById(R.id.TextView_current_ask_price);
        m24Change = view.findViewById(R.id.TextView_price_change);

        mSpinner = view.findViewById(R.id.spinner_timeframe);
        mSpinner.setSelection(2, true);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mModel.getFinnhubAssetCandleData(i);
                if (mListener != null) {
                    updateGraphInterval(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mButtonBuy = view.findViewById(R.id.Button_Buy);
        mButtonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mModel != null) {
                    mModel.addTransaction(Double.valueOf(mEditTextQuantity.getText().toString()));
                }

                mListener.goToPortfolioFragment();
            }
        });
        mButtonSell = view.findViewById(R.id.Button_Sell);
        mButtonSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mModel != null) {
                    mModel.addTransaction(-1 * Double.valueOf(mEditTextQuantity.getText().toString()));

                    mListener.goToPortfolioFragment();
                }
            }
        });

        mNumberKeyboard = view.findViewById(R.id.Keyboard_Number);
        mNumberKeyboard.setInputConnection(mEditTextQuantity.onCreateInputConnection(new EditorInfo()));
    }

    private void showKeyboardForEditTextQuantity() {
        //Request focus and open keyboard
        if (mEditTextQuantity != null && getActivity() != null) {
            mEditTextQuantity.requestFocus();
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mEditTextQuantity.getWindowToken(), 0);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddAssetDetailFragmentInteractionListener) {
            mListener = (OnAddAssetDetailFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddAssetDetailFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        showKeyboardForEditTextQuantity();
    }

    private void updateGraphInterval(int i) {
        XAxis xAxis = mChart.getXAxis();
        DateAxisFormatter formatter = (DateAxisFormatter) xAxis.getValueFormatter();
        formatter.setInterval(i);
        mListener.updateGraphAtInterval(i);
    }

    public void updateGraph(FinnhubAssetCandleData candleData) {
        mNewGraph = true;
        if (candleData == null) {
            mChart.setData(null);
            mChart.invalidate();
            mChart.notifyDataSetChanged();
        }
        List<Entry> entriesUnits = new ArrayList<Entry>();
        try {
            mHigh = -1;
            mLow = -1;
            int minSize = 0;
            minSize = Math.min(candleData.close.size(), candleData.time.size());
            for (int i = 0; i < minSize; i++) {
                long millis = candleData.time.get(i).longValue();
                float close = candleData.close.get(i).floatValue();
                mHigh = Math.max(mHigh, close);
                mLow = Math.max(mLow, close);
                entriesUnits.add(new Entry(millis, close));
            }
            String units = "USD";
            LineDataSet dataSetBTC = new LineDataSet(entriesUnits, units); // add entriesUnits to dataset
            dataSetBTC.setDrawCircles(false);
//        LineDataSet dataSetUSDT = new LineDataSet(entriesUSDT, "USDT"); // add entriesUnits to dataset
//        dataSetBTC.setColor();
//        dataSetBTC.setValueTextColor(...); // styling, ...
            LineData lineData = new LineData(dataSetBTC);
            updateStats();
            mChart.getLegend().setEnabled(false);
            mChart.setData(lineData);
            mChart.invalidate();
            mChart.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public void updateStats () {
        mTextViewHigh.setText(String.valueOf(mHigh));
        mTextViewLow.setText(String.valueOf(mLow));
//            try {
//                JSONObject coinDataJSON = new JSONObject(coinData);
//                JSONArray coinDataArray = coinDataJSON.getJSONArray("result");
//                if (coinDataArray.length() > 0) {
//                    coinDataJSON = coinDataArray.getJSONObject(0);
//                    double high = coinDataJSON.getDouble("High");
//                    double bid = coinDataJSON.getDouble("Bid");
//                    double volume = coinDataJSON.getDouble("Volume");
//                    double low = coinDataJSON.getDouble("Low");
//                    double ask = coinDataJSON.getDouble("Ask");
//                    String currency = "$";
//                    String decimalFormat = "#.00";
//                    DecimalFormat df = new DecimalFormat(decimalFormat);
//                    String _24HourHighStr = currency + df.format(high);
//                    m24High.setText(_24HourHighStr);
//                    String bidStr = currency + df.format(bid);
//                    mBid.setText(bidStr);
//                    String _24HourVolumeStr = currency + new DecimalFormat("#.##").format(volume);
//                    m24Volume.setText(_24HourVolumeStr);
//                    String _24HourLowStr = currency + df.format(low);
//                    m24Low.setText(_24HourLowStr);
//                    String askStr = currency + df.format(ask);
//                    mAsk.setText(askStr);
//                    double change = (coinDataJSON.getDouble("Last") / coinDataJSON.getDouble("PrevDay")) - 1;
//                    change *= 100;
//                    String _24HourChange = (new DecimalFormat("#.##").format(change)) + "%";
//                    m24Change.setText(_24HourChange);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
    }