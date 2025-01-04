package com.stocks.stocksexchange.stockexchangeServices;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocks.stocksexchange.stockexchangeDTOs.TradesDTO;
import com.stocks.stocksexchange.stockexchangeEntities.Stock;
import com.stocks.stocksexchange.stockexchangeEntities.Trades;
import com.stocks.stocksexchange.stockexchangeRepositories.StockRepo;
import com.stocks.stocksexchange.stockexchangeRepositories.TradesRepo;

import jakarta.transaction.Transactional;

@Service
public class TradesService {

    @Autowired
    private TradesRepo tradesRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StockRepo stockRepo;

    // @Autowired
    // private RestTemplate restTemplate;

    public List<TradesDTO> getAllTrades() {
        return tradesRepo.findAll()
                .stream()
                .map(trade -> modelMapper.map(trade, TradesDTO.class))
                .collect(Collectors.toList());
    }

    public TradesDTO createTrade(TradesDTO tradesDTO) {
        Trades trade = modelMapper.map(tradesDTO, Trades.class);
        Trades savedTrade = tradesRepo.save(trade);
        return modelMapper.map(savedTrade, TradesDTO.class);
    }

    public TradesDTO updateTrade(TradesDTO tradesDTO) {
        Trades trade = modelMapper.map(tradesDTO, Trades.class);
        Trades updatedTrade = tradesRepo.save(trade);
        return modelMapper.map(updatedTrade, TradesDTO.class);
    }

    @Transactional
    public int processMarketSellOrder(TradesDTO tradeDTO) {
        // Create the trade record
        Trades trade = modelMapper.map(tradeDTO, Trades.class);
        trade.setTransType("SELL");
        trade.setTypeofsell("MARKET");
        Trades savedTrade = tradesRepo.save(trade);
        
        // Update stock total shares
        Stock stock = stockRepo.findById(trade.getStockId())
            .orElseThrow(() -> new IllegalArgumentException("Stock not found"));
        stock.setTotalShares(stock.getTotalShares() + trade.getNumShares());
        stockRepo.save(stock);
        
        return savedTrade.getNumShares();
    }
    @Transactional
    public int processMarketBuyOrder(TradesDTO tradeDTO) {
        // Create the trade record
        Trades trade = modelMapper.map(tradeDTO, Trades.class);
        trade.setTransType("BUY");
        trade.setTypeofPurchase("MARKET");
        
        // Verify sufficient shares available
        Stock stock = stockRepo.findById(trade.getStockId())
            .orElseThrow(() -> new IllegalArgumentException("Stock not found"));
        if (stock.getTotalShares() < trade.getNumShares()) {
            throw new IllegalArgumentException("Insufficient shares available");
        }
        
        // Update stock total shares
        stock.setTotalShares(stock.getTotalShares() - trade.getNumShares());
        stockRepo.save(stock);
        
        Trades savedTrade = tradesRepo.save(trade);
        return savedTrade.getNumShares();
    }

    //     public List<TradesDTO> getTradesFromExternalApi() {
    //     String url = "http://external-api-url/api/trades"; // Replace with actual URL
    //     ResponseEntity<List<TradesDTO>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<TradesDTO>>() {});
    //     return response.getBody();
    // }

}
