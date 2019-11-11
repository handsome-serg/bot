package com.handsome;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class CoinMarketCupTest {

    private static final Logger log = LoggerFactory.getLogger(CoinMarketCupTest.class);

    @Test
    public void hello(){
        CoinMarketCup.setSandboxMode(true);
        log.info("start testing CoinMarketCup class with url " + CoinMarketCup.getDomainRuntime());
        String responseBody = CoinMarketCup.hello();
        assertNotNull(responseBody);
    }

}