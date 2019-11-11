package com.handsome;

import com.handsome.repository.MarketRepository;
import com.handsome.repository.MarketSummaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bittrex {

    private static final Logger log = LoggerFactory.getLogger(Bittrex.class);

    private MarketRepository marketRepository;
    private MarketSummaryRepository marketSummaryRepository;

    public Bittrex(){
        //default constructor for Spring
    }

    public void hello(){

    }


}
