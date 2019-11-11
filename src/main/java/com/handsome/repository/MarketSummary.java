package com.handsome.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;

/*
MarketSummary entity based on json data from Bittrex API https://bittrex.github.io/api/v1-1

{
      "MarketName": "BTC-LTC",
      "High": 0.0135,
      "Low": 0.012,
      "Volume": 3833.97619253,
      "Last": 0.01349998,
      "BaseVolume": 47.03987026,
      "TimeStamp": "2014-07-09T07:22:16.72",
      "Bid": 0.01271001,
      "Ask": 0.012911,
      "OpenBuyOrders": 45,
      "OpenSellOrders": 45,
      "PrevDay": 0.01229501,
      "Created": "2014-02-13T00:00:00",
      "DisplayMarketName": "string"
    }

 */

@Entity
public class MarketSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonProperty("MarketName")
    private Market market;

    @JsonProperty("High")
    private double high;

    @JsonProperty("Low")
    private double low;

    @JsonProperty("Volume")
    private double volume;

    @JsonProperty("Last")
    private double last;

    @JsonProperty("BaseVolume")
    private double baseVolume;

    @JsonProperty("PrevDay")
    private double prevDay;

    @JsonProperty("Bid")
    private double bid;

    @JsonProperty("Ask")
    private double ask;

    @JsonProperty("OpenBuyOrders")
    private long openBuyOrders;

    @JsonProperty("OpenSellOrders")
    private long openSellOrders;

    @JsonProperty("TimeStamp")
    private Timestamp timestamp;

    @Override
    public String toString(){
        return "" + this.id + " " + this.market +
                " last " + this.last +
                " low "  + this.low  +
                " high " + this.high;
    }

    public double getLast() {
        return last;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public double getBid() {
        return bid;
    }

    public double getAsk() {
        return ask;
    }

}
