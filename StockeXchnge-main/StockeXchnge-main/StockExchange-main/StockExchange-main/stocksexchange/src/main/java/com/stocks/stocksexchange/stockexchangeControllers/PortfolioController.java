package com.stocks.stocksexchange.stockexchangeControllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.stocksexchange.stockexchangeDTOs.BalanceUpdateDTO;
import com.stocks.stocksexchange.stockexchangeDTOs.PortfolioDTO;
import com.stocks.stocksexchange.stockexchangeEntities.Portfolio;
import com.stocks.stocksexchange.stockexchangeServices.PortfolioService;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{accountId}")
    public ResponseEntity<List<PortfolioDTO>> getAccountPortfolios(@PathVariable String accountId) {
        List<Portfolio> portfolios = portfolioService.getPortfoliosByAccountId(accountId);
        List<PortfolioDTO> portfolioDTOs = portfolios.stream()
            .map(portfolio -> modelMapper.map(portfolio, PortfolioDTO.class))
            .collect(Collectors.toList());
        return ResponseEntity.ok(portfolioDTOs);
    }

    @PutMapping("/{portfolioId}/updatebalance")
    public ResponseEntity<Void> updateBalance(@PathVariable String portfolioId, @RequestBody BalanceUpdateDTO balanceUpdateDTO) {
        portfolioService.updateBalance(portfolioId, balanceUpdateDTO.getNewBalance());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/{accountId}/new")
    public ResponseEntity<Void> addNewPortfolio(@PathVariable String accountId, @RequestBody PortfolioDTO portfolioDTO) {
        Portfolio portfolio = modelMapper.map(portfolioDTO, Portfolio.class);
        portfolioService.addHolding(accountId, portfolio);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
