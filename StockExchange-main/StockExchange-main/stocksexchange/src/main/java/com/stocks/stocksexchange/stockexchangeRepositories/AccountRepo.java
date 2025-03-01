package com.stocks.stocksexchange.stockexchangeRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.stocksexchange.stockexchangeEntities.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {

}
