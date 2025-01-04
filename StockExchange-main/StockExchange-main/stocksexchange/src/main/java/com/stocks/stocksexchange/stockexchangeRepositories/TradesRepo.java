package com.stocks.stocksexchange.stockexchangeRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.stocksexchange.stockexchangeEntities.Trades;

@Repository
public interface TradesRepo extends JpaRepository<Trades, Integer> {

}
