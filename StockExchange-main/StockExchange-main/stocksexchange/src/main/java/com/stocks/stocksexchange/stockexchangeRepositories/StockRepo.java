package com.stocks.stocksexchange.stockexchangeRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.stocksexchange.stockexchangeEntities.Stock;

@Repository
public interface StockRepo extends JpaRepository<Stock, Integer> {
    List<Stock> findByType(String type);
}
