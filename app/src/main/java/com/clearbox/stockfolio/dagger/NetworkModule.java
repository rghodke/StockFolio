package com.clearbox.stockfolio.dagger;

import android.content.Context;

import com.clearbox.stockfolio.application.StockfolioApplication;
import com.clearbox.stockfolio.network.FinnhubApiClient;
import com.clearbox.stockfolio.network.FinnhubService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

@Module
public class NetworkModule {

    private static final String GITHUB_SERVER = "https://api.github.com/";
    private StockfolioApplication mContext;

    public NetworkModule(StockfolioApplication context){
        mContext = context;
    }

    @Provides
    @Singleton
    RestAdapter.Builder provideRestAdapterBuilder() {
        return new RestAdapter.Builder().setEndpoint(GITHUB_SERVER)
                .setConverter(new GsonConverter(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                .setLogLevel(RestAdapter.LogLevel.FULL);
    }

    @Provides
    @Singleton
    FinnhubService provideApiService(RestAdapter.Builder restAdapterBuilder) {
        final RestAdapter restAdapter = restAdapterBuilder.build();
        return restAdapter.create(FinnhubService.class);
    }

    @Provides
    @Singleton
    FinnhubApiClient provideApiClient() {
        return new FinnhubApiClient();
    }
}
