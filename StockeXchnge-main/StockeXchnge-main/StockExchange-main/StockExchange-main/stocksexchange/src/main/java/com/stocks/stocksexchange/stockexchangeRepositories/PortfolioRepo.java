package com.stocks.stocksexchange.stockexchangeRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.stocksexchange.stockexchangeEntities.Portfolio;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio, String> {
    List<Portfolio> findByAccountId(String accountId);
}
