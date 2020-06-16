package com.clearbox.stockfolio.network.model;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class FinnhubAssetCandleData {

    public OpenList h;
    public HighList h;
    public LowList l;
    public CloseList c;
    public VolumeList v;
    public TimestampList t;
    public String s;

    private class OpenList {
        List<Double> 
    }
}
