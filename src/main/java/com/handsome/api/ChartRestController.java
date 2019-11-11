package com.handsome.api;

import com.handsome.repository.Market;
import com.handsome.repository.MarketRepository;
import com.handsome.repository.MarketSummary;
import com.handsome.repository.MarketSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chart")
public class ChartRestController {

    @Autowired
    MarketSummaryRepository marketSummaryRepository;

    @Autowired
    MarketRepository marketRepository;

    @GetMapping
    public List<Market> findAll() {
        return marketRepository.findAll();
    }

//    @GetMapping(value = "/{name}")
//    public List<MarketSummary> findById(@PathVariable("name") String name) {
//        return marketSummaryRepository.find;
//    }
}
