package com.stocks.stocksexchange.stockexchangeDTOs;

import lombok.Data;

@Data
public class StockDTO {
    private int stockId;
    private String name;
    private String symbol;
    private int totalShares;
    private double open;
    private double last;
    private boolean status;
    private String type;
}
