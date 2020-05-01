package com.app.idnbin.HomeScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class QuoteData {

        @SerializedName("Ask")
        @Expose
        private Double ask;
        @SerializedName("Bid")
        @Expose
        private Double bid;
        @SerializedName("Symbol")
        @Expose
        private String symbol;
        @SerializedName("Timestamp")
        @Expose
        private String timestamp;

        /**
         * No args constructor for use in serialization
         *
         */
        public QuoteData() {
        }

        /**
         *
         * @param symbol
         * @param ask
         * @param bid
         * @param timestamp
         */
        public QuoteData(Double ask, Double bid, String symbol, String timestamp) {
            super();
            this.ask = ask;
            this.bid = bid;
            this.symbol = symbol;
            this.timestamp = timestamp;
        }

        public Double getAsk() {
            return ask;
        }

        public void setAsk(Double ask) {
            this.ask = ask;
        }

        public Double getBid() {
            return bid;
        }

        public void setBid(Double bid) {
            this.bid = bid;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
}
