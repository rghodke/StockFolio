package com.clearbox.stockfolio.network;

import com.clearbox.stockfolio.application.StockfolioApplication;
import com.clearbox.stockfolio.network.model.FinnhubAsset;
import com.clearbox.stockfolio.network.model.FinnhubAssetListResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class FinnhubApiClient {

    @Inject
    FinnhubService mFinnhubService;

    public FinnhubApiClient(){
        StockfolioApplication.getStockfolioComponent().inject(this);
    }

    public Observable<List<FinnhubAsset>> loadAssets(String exchange) {
        final Observable<List<FinnhubAsset>> getAssetsObservable =
        mFinnhubService.assetList(exchange)
                .flatMap(new Func1<List<FinnhubAsset>, Observable<? extends List<FinnhubAsset>>>() {
                    @Override
                    public Observable<? extends List<FinnhubAsset>> call(List<FinnhubAsset> s) {
                        return Observable.just(s);
                    }
                });

        return getAssetsObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
