package com.clearbox.stockfolio.model;

import org.parceler.Parcel;

@Parcel
public class HeldAsset {

    public String symbol;
    public Double quantity;

    public HeldAsset(String symbol, Double quantity) {
        this.symbol = symbol;
        this.quantity = quantity;
    }
}
