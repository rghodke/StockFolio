package com.clearbox.stockfolio.network;

import com.clearbox.stockfolio.network.model.FinnhubAsset;
import com.clearbox.stockfolio.network.model.FinnhubAssetCandleData;
import com.clearbox.stockfolio.network.model.FinnhubAssetListResponse;


import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface FinnhubService {

    @GET("/stock/symbol")
    Observable<List<FinnhubAsset>> assetList(@Query("exchange") String exchange);

    @GET("/stock/candle")
    Observable<FinnhubAssetCandleData> candleData(@Query("symbol") String symbol, @Query("resolution") String resolution, @Query("from") String from, @Query("to") String to);

    @GET("/users/{user}/repos")
    Observable<String> getRepos(@Path("user") String user);
}
