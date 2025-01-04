package com.stocks.stocksexchange.stockexchangeEntities;

import java.time.LocalDateTime;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeId;

    private String accountId;

    @Size(max = 4)
    private String symbol;

    private int stockId;

    private int numShares;

    private double tradedAt;

    @NotNull
    private LocalDateTime dtime;

    private String transType;

    private String typeofPurchase;

    private String typeofsell;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "accountId", insertable = false, updatable = false, nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "stockId",  insertable = false, updatable = false, nullable = false)
    private Stock stock;

    @PrePersist
    @PreUpdate
    private void validateTradeAndSyncTrade() {
        if (numShares < 0) {
            throw new IllegalArgumentException("Number of shares cannot be negative");
        }
        if (tradedAt < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (dtime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Date and time cannot be in the future");
        }
        if (transType != null && !transType.equals("BUY") && !transType.equals("SELL")) {
            throw new IllegalArgumentException("Transaction type must be either BUY or SELL");
        }
        if (!stock.isStatus()) {
            throw new IllegalStateException("Trading is currently suspended for this stock");
        }
    }
}
