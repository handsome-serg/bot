package com.handsome.config;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.handsome.bittrex.api.ResponseGetMarkets;
import com.handsome.bittrex.api.ResponseGetSummaries;
import com.handsome.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.URL;

@Configuration
@EnableScheduling
@EnableAsync
public class ScheduleConfig {

    private static final Logger log = LoggerFactory.getLogger(ScheduleConfig.class);

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private MarketSummaryRepository marketSummaryRepository;

    //@Async
    @Scheduled(initialDelay = 0 * 1000, fixedRate = 10 * 1000)
    public void scheduledFixedDelayTask() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //  objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
/*
        JsonNode jsonNode = objectMapper.readTree(new URL("https://api.bittrex.com/api/v1.1/public/getmarkets"));

        ObjectReader reader = objectMapper.readerFor(new TypeReference<List<Market>>() {});

        List<Market> marketList = reader.readValue(jsonNode.get("result"));

        for (Market market : marketList) { log.info(market.toString()); }
*/
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Market.class, new MarketDeserializer(marketRepository));
        objectMapper.registerModule(module);

        ResponseGetMarkets responseGetMarkets = objectMapper.readValue( new URL("https://api.bittrex.com/api/v1.1/public/getmarkets"), ResponseGetMarkets.class);

        log.info("Total markets found " + responseGetMarkets.result.size());

//        for (Market market: responseGetMarkets.result
//             ) {
//            //log.trace(market.toString());
//
//            marketRepository.save(market);
//
//        }

        marketRepository.saveAll(responseGetMarkets.result);

        ResponseGetSummaries responseGetSummaries = objectMapper.readValue( new URL("https://api.bittrex.com/api/v1.1/public/getmarketsummaries"), ResponseGetSummaries.class);

        log.info("Total summaries found " + responseGetSummaries.result.size());

//        for (MarketSummary summary: responseGetSummaries.result
//        ) {
//            //log.debug(summary.toString());
//
//            marketSummaryRepository.save(summary);
//
//        }

        marketSummaryRepository.saveAll(responseGetSummaries.result);

        log.info("done");
    }

}
