package com.stocks.stocksexchange.stockexchangeDTOs;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TradesDTO {
    private Long tradeId;
    private String accountId;
    private String symbol;
    private int stockId;
    private int numShares;
    private double tradedAt;
    private LocalDateTime dtime;
    private String transType;
    private String typeofPurchase;
    private String typeofsell;
    private boolean status;
}
