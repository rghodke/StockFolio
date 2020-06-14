package com.clearbox.stockfolio.network;

import com.clearbox.stockfolio.application.StockfolioApplication;
import com.clearbox.stockfolio.network.model.FinnhubResponse;

import javax.inject.Inject;

import retrofit.RestAdapter;
import retrofit.http.Path;
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

    public Observable<FinnhubResponse> getUser(String user) {
        final Observable<FinnhubResponse> getUserObservable =
        mFinnhubService.groupList("defunkt")
                .flatMap(new Func1<FinnhubResponse, Observable<? extends FinnhubResponse>>() {
                    @Override
                    public Observable<? extends FinnhubResponse> call(FinnhubResponse s) {
                        return Observable.just(s);
                    }
                });

        return getUserObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
