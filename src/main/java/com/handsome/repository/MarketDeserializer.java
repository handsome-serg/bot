package com.handsome.repository;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class MarketDeserializer extends JsonDeserializer<Market> {

    private MarketRepository marketRepository;

    public MarketDeserializer(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

//    public MarketDeserializer() {
//        this(null);
//    }
//
//    public MarketDeserializer(Class<?> vc) {
//        super(vc);
//    }

    @Override
    public Market deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        JsonNode node = jp.getCodec().readTree(jp);


        String marketName;

        if (node.isTextual()) {
            marketName = node.asText();
        }else{
            marketName = node.get("MarketName").asText();
        }

        Market market = marketRepository.findFirstByMarketName(marketName);

        if (market == null) {
            market = new Market();
            market.setMarketName(marketName);
        }

        if (node.has("MarketCurrency")) {
            market.setMarketCurrency(       node.get("MarketCurrency").asText());

            //is seems we have all other detailed fields. lets get it

            if (node.has("BaseCurrency")) {
                market.setBaseCurrency(       node.get("BaseCurrency").asText()); }
            if (node.has("MarketCurrencyLong")) {
                market.setMarketCurrencyLong(   node.get("MarketCurrencyLong").asText()); }
            if (node.has("BaseCurrencyLong")) {
                market.setBaseCurrencyLong(     node.get("BaseCurrencyLong").asText()); }
            if (node.has("Notice")) {
                market.setNotice(               node.get("Notice").asText()); }
            if (node.has("LogoUrl")) {
                market.setLogoUrl(               node.get("LogoUrl").asText()); }

            if (node.has("IsActive")) {
                market.setActive(               node.get("IsActive").asBoolean()); }
            if (node.has("IsRestricted")) {
                market.setRestricted(               node.get("IsRestricted").asBoolean()); }
            if (node.has("IsSponsored")) {
                market.setSponsored(               node.get("IsSponsored").asBoolean()); }

            if (node.has("MinTradeSize")) {
                market.setMinTradeSize(node.get("MinTradeSize").asDouble()); }

        }

        return market;
    }

}
