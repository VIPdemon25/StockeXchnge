    package com.stocks.stockexchange.stockexchangeServices;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stocks.stocksexchange.stockexchangeRepositories.PortfolioRepo;
import com.stocks.stocksexchange.stockexchangeServices.PortfolioService;

public class PortfolioServiceTest {

    @InjectMocks
    private PortfolioService portfolioService;

    @Mock
    private PortfolioRepo portfolioRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // @Test
    // public void testGetAllHoldings() {
    //     Portfolio portfolio1 = new Portfolio();
    //     portfolio1.setAccountId(1);
    //     portfolio1.setBalance(1000.00);
    //     portfolio1.setStatus(true);

    //     when(portfolioRepo.findAll()).thenReturn(Arrays.asList(portfolio1));

    //     List<PortfolioDTO> holdings = portfolioService.getAllHoldings();
    //     assertEquals(1, holdings.size());
    //     assertEquals(1000.00, holdings.get(0).getBalance());
    // }

    // @Test
    // public void testAddHolding() {
    //     PortfolioDTO portfolioDTO = new PortfolioDTO();
    //     portfolioDTO.setAccountId(1);
    //     portfolioDTO.setBalance(1500.00);
    //     portfolioDTO.setStatus(true);

    //     Portfolio portfolio = new Portfolio();
    //     portfolio.setAccountId(1);
    //     portfolio.setBalance(1500.00);
    //     portfolio.setStatus(true);

    //     when(portfolioRepo.save(any(Portfolio.class))).thenReturn(portfolio);

    //     PortfolioDTO savedHolding = portfolioService.addHolding(portfolioDTO);
    //     assertEquals(1, savedHolding.getAccountId());
    // }

    // Additional tests for updateBalance() can be added here
}
