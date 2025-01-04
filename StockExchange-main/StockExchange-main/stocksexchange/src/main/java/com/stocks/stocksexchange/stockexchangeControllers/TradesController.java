package com.stocks.stocksexchange.stockexchangeControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.stocksexchange.stockexchangeDTOs.TradesDTO;
import com.stocks.stocksexchange.stockexchangeServices.TradesService;

@RestController
@RequestMapping("/api/stock")
public class TradesController {

    @Autowired
    private TradesService tradesService;

    @PutMapping("/sell/MarketPlan")
    public ResponseEntity<Integer> processMarketSellOrder(@RequestBody TradesDTO tradeDTO) {
        int unitsSold = tradesService.processMarketSellOrder(tradeDTO);
        return new ResponseEntity<>(unitsSold, HttpStatus.OK);
    }

    @PutMapping("/buy/MarketPlan")
    public ResponseEntity<Integer> processMarketBuyOrder(@RequestBody TradesDTO tradeDTO) {
        int unitsBought = tradesService.processMarketBuyOrder(tradeDTO);
        return new ResponseEntity<>(unitsBought, HttpStatus.OK);
    }

    // @GetMapping("/trades") // New endpoint for fetching trades
    // public ResponseEntity<List<TradesDTO>> getTradesLast5Days() {
    //     List<TradesDTO> trades = tradesService.getTradesFromExternalApi();
    //     return new ResponseEntity<>(trades, HttpStatus.OK);
    // }
}
