package com.clearbox.stockfolio.dagger;

import com.clearbox.stockfolio.activity.MainActivity;
import com.clearbox.stockfolio.network.FinnhubApiClient;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        NetworkModule.class
})
public interface StockfolioComponent {

    void inject(MainActivity mainActivity);

    void inject(FinnhubApiClient apiClient);
}
