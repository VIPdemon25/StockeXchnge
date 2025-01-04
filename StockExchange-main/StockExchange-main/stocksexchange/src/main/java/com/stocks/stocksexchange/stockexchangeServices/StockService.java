package com.stocks.stocksexchange.stockexchangeServices;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocks.stocksexchange.stockexchangeDTOs.StockDTO;
import com.stocks.stocksexchange.stockexchangeEntities.Stock;
import com.stocks.stocksexchange.stockexchangeRepositories.PortfolioRepo;
import com.stocks.stocksexchange.stockexchangeRepositories.StockRepo;
import com.stocks.stocksexchange.stockexchangeRepositories.TradesRepo;

@Service
public class StockService {
    @Autowired
    private StockRepo stockRepo;

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    private TradesRepo tradesRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Stock updateStockStatus(Integer stockId, Boolean status){
        Stock stock = stockRepo.findById(stockId).orElseThrow(() -> new IllegalArgumentException("Stock not found"));
        stock.setStatus(status);
        stockRepo.save(stock);

        portfolioRepo.findAll().forEach(portfolio -> {
            portfolio.setStatus(status);
            portfolioRepo.save(portfolio);
        });

        tradesRepo.findAll().forEach(trade -> {
            if(trade.getStock().getStockId() == (stockId)){
                trade.setStatus(status);
                tradesRepo.save(trade);
            }
        });
        return stock;
    }

    public List<StockDTO> getAllStocks() {
        return stockRepo.findAll().stream()
                .map(stock -> modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());
    }

    public StockDTO registerStock(StockDTO stockDTO) {
        Stock stock = modelMapper.map(stockDTO, Stock.class);
        Stock savedStock = stockRepo.save(stock);
        return modelMapper.map(savedStock, StockDTO.class);
    }

    public StockDTO updateStock(StockDTO stockDTO) {
        Stock stock = modelMapper.map(stockDTO, Stock.class);
        Stock updatedStock = stockRepo.save(stock);
        return modelMapper.map(updatedStock, StockDTO.class);
    }

    public List<StockDTO> getStocksByType(String type) {
        return stockRepo.findByType(type).stream()
                .map(stock -> modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());
    }
}
