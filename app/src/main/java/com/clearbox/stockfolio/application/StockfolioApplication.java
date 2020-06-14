package com.clearbox.stockfolio.application;

import android.app.Application;

import com.clearbox.stockfolio.dagger.DaggerStockfolioComponent;
import com.clearbox.stockfolio.dagger.StockfolioComponent;
import com.clearbox.stockfolio.dagger.NetworkModule;

public class StockfolioApplication extends Application {

    private static StockfolioComponent mStockfolioComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        initDaggerObjectGraph();
    }

    public void initDaggerObjectGraph(){
        mStockfolioComponent = DaggerStockfolioComponent.builder().networkModule(new NetworkModule(this)).build();
    }

    public static StockfolioComponent getStockfolioComponent() {
        return mStockfolioComponent;
    }
}
