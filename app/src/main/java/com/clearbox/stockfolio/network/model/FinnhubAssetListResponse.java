package com.clearbox.stockfolio.network.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class FinnhubAssetListResponse {

    @SerializedName("")
    List<FinnhubAsset> finnhubAssetList;

}
