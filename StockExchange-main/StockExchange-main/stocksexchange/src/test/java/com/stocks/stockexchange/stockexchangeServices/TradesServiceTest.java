package com.stocks.stockexchange.stockexchangeServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.stocks.stocksexchange.stockexchangeDTOs.TradesDTO;
import com.stocks.stocksexchange.stockexchangeEntities.Trades;
import com.stocks.stocksexchange.stockexchangeRepositories.TradesRepo;
import com.stocks.stocksexchange.stockexchangeServices.TradesService;

public class TradesServiceTest {

    @InjectMocks
    private TradesService tradesService;

    @Mock
    private TradesRepo tradesRepo;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTrades() {
        Trades trade1 = new Trades();
        trade1.setTradeId((long) 1);
        Trades trade2 = new Trades();
        trade2.setTradeId((long) 2);
        when(tradesRepo.findAll()).thenReturn(Arrays.asList(trade1, trade2));

        List<TradesDTO> trades = tradesService.getAllTrades();
        assertEquals(2, trades.size());
    }

    @Test
    public void testCreateTrade() {
        TradesDTO tradesDTO = new TradesDTO();
        tradesDTO.setTradeId((long) 1);
        Trades trade = new Trades();
        trade.setTradeId((long) 1);
        when(tradesRepo.save(any(Trades.class))).thenReturn(trade);

        TradesDTO createdTrade = tradesService.createTrade(tradesDTO);
        assertEquals(1, createdTrade.getTradeId());
    }

    @Test
    public void testUpdateTrade() {
        TradesDTO tradesDTO = new TradesDTO();
        tradesDTO.setTradeId((long) 1);
        Trades trade = new Trades();
        trade.setTradeId((long) 1);
        when(tradesRepo.save(any(Trades.class))).thenReturn(trade);

        TradesDTO updatedTrade = tradesService.updateTrade(tradesDTO);
        assertEquals(1, updatedTrade.getTradeId());
    }

    @Test
    public void testProcessMarketSellOrder() {
        TradesDTO tradesDTO = new TradesDTO();
        tradesDTO.setNumShares(10);
        Trades trade = new Trades();
        trade.setNumShares(10);
        when(tradesRepo.save(any(Trades.class))).thenReturn(trade);

        int unitsSold = tradesService.processMarketSellOrder(tradesDTO);
        assertEquals(10, unitsSold);
    }

    @Test
    public void testProcessMarketBuyOrder() {
        TradesDTO tradesDTO = new TradesDTO();
        tradesDTO.setNumShares(15);
        Trades trade = new Trades();
        trade.setNumShares(15);
        when(tradesRepo.save(any(Trades.class))).thenReturn(trade);

        int unitsBought = tradesService.processMarketBuyOrder(tradesDTO);
        assertEquals(15, unitsBought);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetTradesFromExternalApi() {
        // Mocking the external API response
        ResponseEntity<List<TradesDTO>> responseEntity = mock(ResponseEntity.class);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(Arrays.asList(new TradesDTO()));

        // List<TradesDTO> trades = tradesService.getTradesFromExternalApi();
        // assertNotNull(trades);
    }
}
