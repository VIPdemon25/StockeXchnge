package com.stocks.stocksexchange.stockexchangeDTOs;

import lombok.Data;

@Data
public class PortfolioDTO {
    private String portfolioId;
    private String accountId;
    private double balance;
    private boolean status;
}
