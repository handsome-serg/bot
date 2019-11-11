
//According to API data
//    {
//        "MarketCurrency": "LTC",
//            "BaseCurrency": "BTC",
//            "MarketCurrencyLong": "Litecoin",
//            "BaseCurrencyLong": "Bitcoin",
//            "MinTradeSize": 0.01,
//            "MarketName": "BTC-LTC",
//            "IsActive": true,
//            "IsRestricted": false,
//            "Created": "2014-02-13T00:00:00",
//            "Notice": "BTC-LTC",
//            "IsSponsored": false,
//            "LogoUrl": "https://storage.blob.core.windows.net/public/8637ccad-9e7f-45ac-8f03-a41b440e3911.png"
//    }

package com.handsome.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "market", indexes = {
        @Index(columnList = "market_name", name = "market_name_idx", unique = true)
})
//@JsonDeserialize(using = MarketDeserializer.class)
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NaturalId
    @Column(name = "market_name", length = 11, updatable = false, nullable = false, unique = true)
    @JsonProperty("MarketName")
    private String marketName;

    @JsonProperty("LogoUrl")
    private String logoUrl;

    @JsonProperty("MarketCurrency")
    private String marketCurrency;
    @JsonProperty("BaseCurrency")
    private String baseCurrency;
    @JsonProperty("MarketCurrencyLong")
    private String marketCurrencyLong;
    @JsonProperty("BaseCurrencyLong")
    private String baseCurrencyLong;
    @JsonProperty("Notice")
    private String notice;

    @JsonProperty("MinTradeSize")
    private double minTradeSize;

    @JsonProperty("IsActive")
    private boolean isActive;
    @JsonProperty("IsRestricted")
    private boolean isRestricted;
    @JsonProperty("IsSponsored")
    private boolean isSponsored;

    public Market(){}

    public Market(String marketName){
        this.marketName = marketName;
    }

    @Override
    public String toString(){
        return "" + this.marketName + " (id " + this.id + " , logo " + this.logoUrl + ")";
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getMarketCurrency() {
        return marketCurrency;
    }

    public void setMarketCurrency(String marketCurrency) {
        this.marketCurrency = marketCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getMarketCurrencyLong() {
        return marketCurrencyLong;
    }

    public void setMarketCurrencyLong(String marketCurrencyLong) {
        this.marketCurrencyLong = marketCurrencyLong;
    }

    public String getBaseCurrencyLong() {
        return baseCurrencyLong;
    }

    public void setBaseCurrencyLong(String baseCurrencyLong) {
        this.baseCurrencyLong = baseCurrencyLong;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public double getMinTradeSize() {
        return minTradeSize;
    }

    public void setMinTradeSize(double minTradeSize) {
        this.minTradeSize = minTradeSize;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isRestricted() {
        return isRestricted;
    }

    public void setRestricted(boolean restricted) {
        isRestricted = restricted;
    }

    public boolean isSponsored() {
        return isSponsored;
    }

    public void setSponsored(boolean sponsored) {
        isSponsored = sponsored;
    }
}
