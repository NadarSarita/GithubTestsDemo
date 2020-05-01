package com.app.idnbin.SymbolInfo;

public class OvernightFundingData {
    String overnightFundingDay,overnightFundingTime, overnightFundingSell, overnightFundingBuy;

    public OvernightFundingData(String overnightFundingDay, String overnightFundingTime, String overnightFundingSell, String overnightFundingBuy) {
        this.overnightFundingDay = overnightFundingDay;
        this.overnightFundingTime = overnightFundingTime;
        this.overnightFundingSell = overnightFundingSell;
        this.overnightFundingBuy = overnightFundingBuy;
    }

    public String getOvernightFundingDay() {
        return overnightFundingDay;
    }

    public void setOvernightFundingDay(String overnightFundingDay) {
        this.overnightFundingDay = overnightFundingDay;
    }

    public String getOvernightFundingTime() {
        return overnightFundingTime;
    }

    public void setOvernightFundingTime(String overnightFundingTime) {
        this.overnightFundingTime = overnightFundingTime;
    }

    public String getOvernightFundingSell() {
        return overnightFundingSell;
    }

    public void setOvernightFundingSell(String overnightFundingSell) {
        this.overnightFundingSell = overnightFundingSell;
    }

    public String getOvernightFundingBuy() {
        return overnightFundingBuy;
    }

    public void setOvernightFundingBuy(String overnightFundingBuy) {
        this.overnightFundingBuy = overnightFundingBuy;
    }
}
