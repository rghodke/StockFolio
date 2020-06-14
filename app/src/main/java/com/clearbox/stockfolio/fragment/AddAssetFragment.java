package com.clearbox.stockfolio.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.clearbox.stockfolio.R;
import com.clearbox.stockfolio.adapter.FinnhubAssetItemRecyclerViewAdapter;
import com.clearbox.stockfolio.application.StockfolioApplication;
import com.clearbox.stockfolio.fragment.dummy.DummyContent;
import com.clearbox.stockfolio.network.model.FinnhubAsset;
import com.clearbox.stockfolio.viewmodel.StockfolioViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class AddAssetFragment extends Fragment {

    private StockfolioViewModel mModel;
    private FinnhubAssetItemRecyclerViewAdapter mAssetAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AddAssetFragment() {
        StockfolioApplication.getStockfolioComponent().inject(this);
    }

    @SuppressWarnings("unused")
    public static AddAssetFragment newInstance() {
        AddAssetFragment fragment = new AddAssetFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_asset, container, false);
        setupViews(view);

        return view;
    }

    private void setupViews(View view) {
        final Context context = view.getContext();

        mAssetAdapter = new FinnhubAssetItemRecyclerViewAdapter(new ArrayList<>());

        if (getActivity() != null)
            mModel = ViewModelProviders.of(getActivity()).get(StockfolioViewModel.class);

        if (mModel != null) {
            mModel.getAssets().observe(this, new Observer<List<FinnhubAsset>>() {
                @Override
                public void onChanged(List<FinnhubAsset> finnhubAssets) {
                    mAssetAdapter.setData(finnhubAssets);
                }
            });
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView_Add_Asset);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAssetAdapter);

        final EditText mSearchEditText = view.findViewById(R.id.EditText_Search);
        mSearchEditText.requestFocus();
        mSearchEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager)
                        context.getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(mSearchEditText, 0);
            }
        },200);
    }

}