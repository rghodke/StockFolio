package com.clearbox.stockfolio.network.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel public class FinnhubAssetStockData {

    @SerializedName("o")
    public Double open;
    @SerializedName("h")
    public Double high;
    @SerializedName("l")
    public Double low;
    @SerializedName("c")
    public Double close;
    @SerializedName("pc")
    public Double previousClose;
    @SerializedName("t")
    public Double time;

}
