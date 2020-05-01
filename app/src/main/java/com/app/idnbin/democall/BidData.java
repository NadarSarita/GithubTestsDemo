package com.app.idnbin.democall;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BidData {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("bid")
    @Expose
    private Float bid;
    @SerializedName("ask")
    @Expose
    private Double ask;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

}