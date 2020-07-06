package com.clearbox.stockfolio.application;

import android.app.Application;

import com.clearbox.stockfolio.dagger.DaggerStockfolioComponent;
import com.clearbox.stockfolio.dagger.StockfolioComponent;
import com.clearbox.stockfolio.dagger.NetworkModule;

public class StockfolioApplication extends Application {

    private static StockfolioComponent mStockfolioComponent;
    private static StockfolioApplication mInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
        initDaggerObjectGraph();
    }

    public void initDaggerObjectGraph(){
        mStockfolioComponent = DaggerStockfolioComponent.builder().networkModule(new NetworkModule(this)).build();
    }

    public static StockfolioComponent getStockfolioComponent() {
        return mStockfolioComponent;
    }

    public static StockfolioApplication getInstance() {
        return mInstance;
    }
}
