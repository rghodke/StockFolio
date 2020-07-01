package com.clearbox.stockfolio.network;

import android.media.browse.MediaBrowser;

import com.clearbox.stockfolio.application.StockfolioApplication;
import com.clearbox.stockfolio.network.model.FinnhubAsset;
import com.clearbox.stockfolio.network.model.FinnhubAssetCandleData;
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

    public Observable<FinnhubAssetCandleData> loadCandleData(String symbol, String resolution, String from, String to) {
        final Observable<FinnhubAssetCandleData> getCandleDataObservable =
                mFinnhubService.candleData(symbol, resolution, from, to)
                        .flatMap(new Func1<FinnhubAssetCandleData, Observable<? extends FinnhubAssetCandleData>>() {
                            @Override
                            public Observable<? extends FinnhubAssetCandleData> call(FinnhubAssetCandleData s) {
                                return Observable.just(s);
                            }
                        });

        return getCandleDataObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<FinnhubAssetStockData> loadStockData(String symbol) {
        final Observable<FinnhubAssetStockData> getStockDataObservable =
                mFinnhubService.stockData(symbol)
                        .flatMap(new Func1<FinnhubAssetStockData, Observable<? extends FinnhubAssetStockData>>() {
                            @Override
                            public Observable<? extends FinnhubAssetStockData> call(FinnhubAssetStockData s) {
                                return Observable.just(s);
                            }
                        });

        return getStockDataObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
