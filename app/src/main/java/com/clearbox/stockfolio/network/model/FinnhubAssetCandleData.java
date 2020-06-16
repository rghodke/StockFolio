package com.clearbox.stockfolio.network.model;

import org.parceler.Parcel;

import java.util.List;

@Parcel public class FinnhubAssetCandleData {

    public List<Double> o;
    public List<Double> h;
    public List<Double> l;
    public List<Double> c;
    public List<Double> v;
    public List<Double> t;
    public String s;

}
