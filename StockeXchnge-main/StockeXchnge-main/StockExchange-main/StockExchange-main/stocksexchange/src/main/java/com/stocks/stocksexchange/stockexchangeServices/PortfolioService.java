package com.stocks.stocksexchange.stockexchangeServices;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocks.stocksexchange.stockexchangeDTOs.PortfolioDTO;
import com.stocks.stocksexchange.stockexchangeEntities.Account;
import com.stocks.stocksexchange.stockexchangeEntities.Portfolio;
import com.stocks.stocksexchange.stockexchangeRepositories.AccountRepo;
import com.stocks.stocksexchange.stockexchangeRepositories.PortfolioRepo;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<PortfolioDTO> getAllHoldings() {
        return portfolioRepo.findAll().stream()
            .map(portfolio -> modelMapper.map(portfolio, PortfolioDTO.class))
            .collect(Collectors.toList());
    }


    public PortfolioDTO addHolding(String accountId, Portfolio portfolio) {
        Account account = accountRepo.findById(portfolio.getAccountId())
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        portfolio.setAccount(account);
        Portfolio savedPortfolio = portfolioRepo.save(portfolio);
        return modelMapper.map(savedPortfolio, PortfolioDTO.class);
    }



 

    public void updateBalance(String portfolioId, double newBalance) {
        Portfolio portfolio = portfolioRepo.findById(portfolioId)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found"));
        portfolio.setBalance(newBalance);
        portfolioRepo.save(portfolio);
    }


    public List<Portfolio> getPortfoliosByAccountId(String accountId) {
        return portfolioRepo.findByAccountId(accountId);
    }
    
}
