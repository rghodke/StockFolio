package com.clearbox.stockfolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.clearbox.stockfolio.R;
import com.clearbox.stockfolio.fragment.*;
import com.clearbox.stockfolio.network.FinnhubApiClient;
import com.clearbox.stockfolio.network.model.FinnhubResponse;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private static final String PORTFOLIO_FRAG = "PortfolioFragment";
    private static final String ADD_ASSET_FRAG = "AddAssetFragment";
    private FrameLayout mFragmentHolder;
    private PortfolioFragment mPortfolioFragment;
    private AddAssetFragment mAddAssetFragment;

    private ImageButton mAddTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupPortfolioFragment();

        FinnhubApiClient.getApiClient().getUser("defunkt").subscribe(new Subscriber<FinnhubResponse>() {
            @Override
            public void onCompleted() {
                System.out.println("MainActivity.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(FinnhubResponse s) {
                System.out.println("s = " + s.getLogin());
            }
        });
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