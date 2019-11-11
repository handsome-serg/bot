package com.handsome.controller;

import com.handsome.repository.MarketRepository;
import com.handsome.repository.MarketSummary;
import com.handsome.repository.MarketSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Locale;

@Controller()
@RequestMapping("/api")
public class ApiController {

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    MarketSummaryRepository marketSummaryRepository;

    @RequestMapping(path = "/chart/market/{marketName}/{pageSize:[\\d]+}", produces = "application/json")

    public @ResponseBody String getMarketChartData(
            @PathVariable("marketName") String marketName, @PathVariable long pageSize){

        List<MarketSummary> marketSummaries = marketSummaryRepository.findFirst50ByMarket_MarketNameOrderByIdDesc(marketName);

        //the fastest way to build a simple data output
        StringBuilder sb = new StringBuilder();

        //column names
        sb.append(String.format("[[\"%s\",\"last\",\"bid\",\"ask\"]", marketName));

        for (MarketSummary marketSummary: marketSummaries)
        {
            sb.append(
                    String.format(Locale.ROOT,",[\"%s\",%f,%f,%f]", //decimal delimiter must be a dot symbol '.'
                        marketSummary.getTimestamp(),
                        marketSummary.getLast(),
                        marketSummary.getBid(),
                        marketSummary.getAsk()
                    ));
        }

        sb.append("]");

        String result = sb.toString();

        return result;

    }

}
