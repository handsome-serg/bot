package com.handsome.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarketSummaryRepository extends CrudRepository<MarketSummary, Long> {

    //List<MarketSummary> findByMarketNameStartsWithIgnoreCase(String marketName);

    List<MarketSummary> findFirst50ByMarket_MarketNameOrderByIdDesc(String marketName);

}
