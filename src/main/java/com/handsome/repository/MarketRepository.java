
//to get sql log use this props:

//spring.jpa.properties.hibernate.show_sql=true
//spring.jpa.properties.hibernate.use_sql_comments=true
//spring.jpa.properties.hibernate.format_sql=true

//spring.jpa.properties.hibernate.type=trace

package com.handsome.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "market", path = "market")
public interface MarketRepository  extends PagingAndSortingRepository<Market, Long> {

    //List<Market> findByMarketNameStartsWithIgnoreCase(String marketName);
    Market findFirstByMarketName(String marketName);
    List<Market> findAll();

}
