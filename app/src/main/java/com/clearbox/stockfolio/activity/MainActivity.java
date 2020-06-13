package com.clearbox.stockfolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.clearbox.stockfolio.R;

public class MainActivity extends AppCompatActivity {

    private static final String PORTFOLIO_FRAG = "PortfolioFragment";
    private FrameLayout mFragmentHolder;
    private fragment.PortfolioFragment mPortfolioFragment;

    private ImageButton mAddTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupPortfolioFragment();
    }

    private void setupViews() {
        mFragmentHolder = findViewById(R.id.FrameLayout_fragment_holder);
        mAddTransaction = findViewById(R.id.ImageButton_Add_Transaction);

        mAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddTransactionFragment();
            }
        });
    }

    private void goToAddTransactionFragment() {
        //TODO: Implement Add Transaction Fragment
    }

    private void setupPortfolioFragment() {
        //Create a fragment if not already created
        if (mPortfolioFragment == null) {
            mPortfolioFragment = fragment.PortfolioFragment.newInstance();
        }

        //Setup the fragment holder as necessary
        if (mFragmentHolder == null) setupViews();

        //Fail-safe null check
        if (mFragmentHolder != null) {
            getSupportFragmentManager().beginTransaction().add(mFragmentHolder.getId(),mPortfolioFragment).addToBackStack(PORTFOLIO_FRAG).commit();
        }
    }
}