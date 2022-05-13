package com.clearbox.stockfolio.model;

import org.parceler.Parcel;

@Parcel
public class HeldAsset {

    public String symbol;
    public Double currentPrice;
    public Double quantity;

    public HeldAsset(String symbol, Double quantity, Double stockPrice) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.currentPrice = stockPrice;
    }
}
