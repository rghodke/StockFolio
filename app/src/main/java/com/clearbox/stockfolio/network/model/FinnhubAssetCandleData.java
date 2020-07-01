package com.clearbox.stockfolio.network.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel public class FinnhubAssetCandleData {

    @SerializedName("o")
    public List<Double> open;
    @SerializedName("h")
    public List<Double> high;
    @SerializedName("l")
    public List<Double> low;
    @SerializedName("c")
    public List<Double> close;
    @SerializedName("v")
    public List<Double> volume;
    @SerializedName("t")
    public List<Double> time;
    @SerializedName("s")
    public String status;

}
