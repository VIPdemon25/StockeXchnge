package com.stocks.stocksexchange.stockexchangeRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.stocksexchange.stockexchangeEntities.Portfolio;

import java.util.List;
import java.util.UUID;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio, UUID> {
    List<Portfolio> findByAccountId(String accountId);
}
