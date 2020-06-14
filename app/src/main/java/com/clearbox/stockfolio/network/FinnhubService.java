package com.clearbox.stockfolio.network;

import com.clearbox.stockfolio.network.model.FinnhubAsset;
import com.clearbox.stockfolio.network.model.FinnhubAssetListResponse;


import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface FinnhubService {

    @GET("/stock/symbol")
    Observable<List<FinnhubAsset>> assetList(@Query("exchange") String exchange);

    @GET("/users/{user}/repos")
    Observable<String> getRepos(@Path("user") String user);
}
