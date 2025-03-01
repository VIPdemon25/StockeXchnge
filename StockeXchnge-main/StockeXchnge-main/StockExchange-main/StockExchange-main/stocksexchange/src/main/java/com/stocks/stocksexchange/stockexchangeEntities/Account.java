package com.stocks.stocksexchange.stockexchangeEntities;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;


@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(unique = true , nullable = false)
    private String accountId;
    private String fname;
    private String lname;
    @Column(unique = true , nullable = false)
    private String email;
    private boolean status;
    @Transient
    private String name;

    @PrePersist
    @PreUpdate
    private void generateAccountId(){
        if(fname.length() < 3 || lname.length() < 3){
            throw new IllegalArgumentException("First name and Last name should be at least 3 characters long");
        }
        if(!email.endsWith("@cognizant.com")){
            throw new IllegalArgumentException("Only Cognizant email ids are allowed");
        }
        String uniqueId = String.format("%04d", new Random().nextInt(10000));
        String prefix = lname.substring(0, 2).toUpperCase();
        this.accountId =  prefix + uniqueId ;
        this.name = fname + " " + lname;
    }

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Portfolio> portfolios = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Trades> trades;

}
