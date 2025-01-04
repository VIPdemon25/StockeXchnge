package com.stocks.stocksexchange.stockexchangeControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.stocksexchange.stockexchangeDTOs.StockDTO;
import com.stocks.stocksexchange.stockexchangeServices.StockService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/companies/type")
    public ResponseEntity<List<StockDTO>> getStocksByType(@RequestParam String type) {
        List<StockDTO> stocks = stockService.getStocksByType(type);
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        List<StockDTO> stocks = stockService.getAllStocks();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @PostMapping("/companies/new")
    public ResponseEntity<Void> registerNewStock(@RequestBody StockDTO stockDTO) {
        stockService.registerStock(stockDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/companies/{stockId}/update")
    public ResponseEntity<Void> updateCompanyInfo(@PathVariable Integer stockId, @RequestBody StockDTO stockDTO) {
        stockDTO.setStockId(stockId);
        stockService.updateStock(stockDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStockStatus(@PathVariable Integer id, @RequestBody Boolean status) {
        stockService.updateStockStatus(id, status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
