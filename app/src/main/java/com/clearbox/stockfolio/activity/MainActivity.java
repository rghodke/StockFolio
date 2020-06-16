package com.clearbox.stockfolio.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.clearbox.stockfolio.R;
import com.clearbox.stockfolio.application.StockfolioApplication;
import com.clearbox.stockfolio.fragment.*;
import com.clearbox.stockfolio.network.FinnhubApiClient;
import com.clearbox.stockfolio.network.model.FinnhubAsset;
import com.clearbox.stockfolio.viewmodel.StockfolioViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements AddAssetFragment.AddAssetFragmentInteractionListener, AddAssetDetailFragment.OnAddAssetDetailFragmentInteractionListener {

    @Inject
    FinnhubApiClient mFinnhubApiClient;

    private static final String PORTFOLIO_FRAG = "PortfolioFragment";
    private static final String ADD_ASSET_FRAG = "AddAssetFragment";
    private static final String ADD_TRANS_FRAG = "AddTransactionFragment";
    private FrameLayout mFragmentHolder;
    private PortfolioFragment mPortfolioFragment;
    private AddAssetFragment mAddAssetFragment;
    private AddAssetDetailFragment mAddAssetDetailFragment;

    private ImageButton mAddTransaction;

    private StockfolioViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StockfolioApplication.getStockfolioComponent().inject(this);

        mModel = ViewModelProviders.of(this).get(StockfolioViewModel.class);

        setupViews();
        setupPortfolioFragment();
    }

    private void setupViews() {
        mFragmentHolder = findViewById(R.id.FrameLayout_fragment_holder);
    }

    private void setupPortfolioFragment() {
        //Create a fragment if not already created
        if (mPortfolioFragment == null) {
            mPortfolioFragment = PortfolioFragment.newInstance();
        }

        //Setup the fragment holder as necessary
        if (mFragmentHolder == null) setupViews();

        //Fail-safe null check
        if (mFragmentHolder != null) {
            getSupportFragmentManager().beginTransaction().replace(mFragmentHolder.getId(), mPortfolioFragment).addToBackStack(PORTFOLIO_FRAG).commit();
        }
    }

    public void goToAddAssetFragment(View view) {
        //Create a fragment if not already created
        if (mAddAssetFragment == null) {
            mAddAssetFragment = AddAssetFragment.newInstance();
        }

        //Setup the fragment holder as necessary
        if (mFragmentHolder == null) setupViews();

        //Fail-safe null check
        if (mFragmentHolder != null) {
            getSupportFragmentManager().beginTransaction().replace(mFragmentHolder.getId(), mAddAssetFragment).addToBackStack(ADD_ASSET_FRAG).commit();
        }
    }

    public void goToAddTransactionFragment() {
        //Create a fragment if not already created
        if (mAddAssetDetailFragment == null) {
            mAddAssetDetailFragment = AddAssetDetailFragment.newInstance();
        }

        //Setup the fragment holder as necessary
        if (mFragmentHolder == null) setupViews();

        //Fail-safe null check
        if (mFragmentHolder != null) {
            getSupportFragmentManager().beginTransaction().replace(mFragmentHolder.getId(), mAddAssetDetailFragment).addToBackStack(ADD_TRANS_FRAG).commit();
        }
    }

    @Override
    public void onFinnhubAssetListItemClicked(final FinnhubAsset item) {
        mModel.onFinnhubAssetSelected(item);

        System.out.println(item.symbol);

        goToAddTransactionFragment();
    }

    @Override
    public void updateGraphAtInterval(int i) {
//        if (mCoinGraph != null) mCoinGraph.removeCallbacksAndMessages(null);
        updateCoinGraph(i);
    }

    @Override
    public void startCoinGraphDataService() {
        updateCoinGraph(2); //Default is 1 day
    }

    private void updateCoinGraph(final int i) {
//        //Every second get the newest info about the coin you want
//        //Reset handler if already running
//        if (mCoinGraph != null) mCoinGraph.removeCallbacksAndMessages(null);
//        mCoinGraph = new Handler();
//        final int delay = 1000; //milliseconds
//
//        mCoinGraph.postDelayed(new Runnable() {
//            public void run() {
//                mService.getMarketDataForCurrency();
//                mService.getUSDTBTCPrice();
//                //do something
//                switch (i) {
//                    case 0:
//                        mService.getMinuteDataForOneHour();
//                        break;
//                    case 1:
//                        mService.getHalfHourDataForPast12Hours();
//                        break;
//                    case 2:
//                        mService.getHourlyDataForPast24Hours();
//                        break;
//                    case 3:
//                        mService.getThreeHourlyDataForPast3Days();
//                        break;
//                    case 4:
//                        mService.getSixHourlyDataForPast1Week();
//                        break;
//                    case 5:
//                        mService.getDailyDataForPast1Month();
//                        break;
//                    case 6:
//                        mService.getThreeDaysDataForPast3Month();
//                        break;
//                    case 7:
//                        mService.getWeeklyDataForPast6Month();
//                        break;
//                    default:
//                        mService.getHourlyDataForPast24Hours();
//                        break;
//                }
//                mCoinGraph.postDelayed(this, delay);
//
//            }
//        }, delay);
    }
}