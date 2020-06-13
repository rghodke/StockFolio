package com.clearbox.stockfolio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.clearbox.stockfolio.R;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mFragmentHolder;
    private fragment.PortfolioFragment mPortfolioFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupFragment();
    }

    private void setupViews() {
        mFragmentHolder = findViewById(R.id.FrameLayout_fragment_holder);
    }

    private void setupFragment() {
        //Create a fragment if not already created
        if (mPortfolioFragment == null) {
            mPortfolioFragment = fragment.PortfolioFragment.newInstance();
        }

        //Setup the fragment holder as necessary
        if (mFragmentHolder == null) setupViews();

        //Fail-safe null check
        if (mFragmentHolder != null) {
            getSupportFragmentManager().beginTransaction().add(mFragmentHolder.getId(),mPortfolioFragment).commit();
        }
    }
}