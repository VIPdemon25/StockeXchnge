package com.stocks.stockexchange.stockexchangeServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stocks.stocksexchange.stockexchangeDTOs.StockDTO;
import com.stocks.stocksexchange.stockexchangeEntities.Stock;
import com.stocks.stocksexchange.stockexchangeRepositories.StockRepo;
import com.stocks.stocksexchange.stockexchangeServices.StockService;

public class StockServiceTest {

    @InjectMocks
    private StockService stockService;

    @Mock
    private StockRepo stockRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllStocks() {
        Stock stock1 = new Stock();
        stock1.setStockId(1);
        stock1.setName("Example Stock");
        stock1.setSymbol("EXMPL");
        stock1.setTotalShares(10000);
        stock1.setOpen(50.0);
        stock1.setLast(55.0);
        stock1.setStatus(true);

        when(stockRepo.findAll()).thenReturn(Arrays.asList(stock1));

        List<StockDTO> stocks = stockService.getAllStocks();
        assertEquals(1, stocks.size());
        assertEquals("Example Stock", stocks.get(0).getName());
    }

    @Test
    public void testRegisterStock() {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setName("New Stock");
        stockDTO.setSymbol("NEW");

        Stock stock = new Stock();
        stock.setStockId(2);
        stock.setName("New Stock");
        stock.setSymbol("NEW");

        when(stockRepo.save(any(Stock.class))).thenReturn(stock);

        StockDTO savedStock = stockService.registerStock(stockDTO);
        assertEquals("New Stock", savedStock.getName());
    }

    // Additional tests for updateStock() and other methods can be added here
}
