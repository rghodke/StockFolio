package com.clearbox.stockfolio.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.clearbox.stockfolio.R;
import com.clearbox.stockfolio.application.StockfolioApplication;
import com.clearbox.stockfolio.fragment.*;
import com.clearbox.stockfolio.network.FinnhubApiClient;
import com.clearbox.stockfolio.network.model.FinnhubAsset;
import com.clearbox.stockfolio.network.model.FinnhubAssetListResponse;
import com.clearbox.stockfolio.viewmodel.StockfolioViewModel;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    @Inject
    FinnhubApiClient mFinnhubApiClient;

    private static final String PORTFOLIO_FRAG = "PortfolioFragment";
    private static final String ADD_ASSET_FRAG = "AddAssetFragment";
    private FrameLayout mFragmentHolder;
    private PortfolioFragment mPortfolioFragment;
    private AddAssetFragment mAddAssetFragment;

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

    public void goToAddTransactionFragment(View view) {
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
}