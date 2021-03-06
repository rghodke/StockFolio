package com.clearbox.stockfolio.dagger;

import com.clearbox.stockfolio.activity.MainActivity;
import com.clearbox.stockfolio.fragment.AddAssetFragment;
import com.clearbox.stockfolio.fragment.PortfolioFragment;
import com.clearbox.stockfolio.network.FinnhubApiClient;
import com.clearbox.stockfolio.viewmodel.StockfolioViewModel;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        NetworkModule.class
})
public interface StockfolioComponent {

    void inject(MainActivity mainActivity);

    void inject(FinnhubApiClient apiClient);

    void inject(StockfolioViewModel stockfolioViewModel);

    void inject(PortfolioFragment portfolioFragment);

    void inject(AddAssetFragment addAssetFragment);
}
