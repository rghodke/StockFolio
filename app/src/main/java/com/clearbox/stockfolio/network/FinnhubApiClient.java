package com.clearbox.stockfolio.network;

import com.clearbox.stockfolio.network.model.FinnhubResponse;

import retrofit.RestAdapter;
import retrofit.http.Path;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class FinnhubApiClient {

    //TODO: Use Retrofit2 instead. Update all libraries
    private static FinnhubApiClient mFinnhubApiClient;
    private static FinnhubService mFinnhubService;

    public FinnhubApiClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com/")
                .build();

        mFinnhubService = restAdapter.create(FinnhubService.class);
    }

    public static FinnhubApiClient getApiClient() {
        if (mFinnhubApiClient == null) {
            mFinnhubApiClient = new FinnhubApiClient();
        }
        return mFinnhubApiClient;
    }

    public static FinnhubService getFinnhubService() {
        if (mFinnhubService == null || mFinnhubApiClient == null) {
            mFinnhubApiClient = new FinnhubApiClient();
        }
        return mFinnhubService;
    }

    public Observable<FinnhubResponse> getUser(String user) {
        final Observable<FinnhubResponse> getUserObservable =
        FinnhubApiClient.getFinnhubService().groupList("defunkt")
                .flatMap(new Func1<FinnhubResponse, Observable<? extends FinnhubResponse>>() {
                    @Override
                    public Observable<? extends FinnhubResponse> call(FinnhubResponse s) {
                        return Observable.just(s);
                    }
                });

        return getUserObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
