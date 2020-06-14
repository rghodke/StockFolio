package com.clearbox.stockfolio.network;

import com.clearbox.stockfolio.network.model.FinnhubResponse;


import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface FinnhubService {

    @GET("/users/{id}")
    Observable<FinnhubResponse> groupList(@Path("id") String user);

    @GET("/users/{user}/repos")
    Observable<String> getRepos(@Path("user") String user);
}
