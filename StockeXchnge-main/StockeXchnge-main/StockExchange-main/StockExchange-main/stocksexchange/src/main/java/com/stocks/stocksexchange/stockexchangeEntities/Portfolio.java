package com.stocks.stocksexchange.stockexchangeEntities;

import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portfolios")
public class Portfolio {
    @Id
    private String portfolioId;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private double balance = 0.0;

    private boolean status = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", insertable = false, updatable = false)
    private Account account;

    @PrePersist
    private void generatePortfolioId(){
        if(this.portfolioId == null && this.accountId != null){
            String randomSuffix = String.format("%04d", new Random().nextInt(10000));
            this.portfolioId = this.accountId + "_p" + randomSuffix;
        }
    }

    @PreUpdate
    private void validateBalance() {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
    }
}
