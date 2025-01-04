package com.stocks.stocksexchange.stockexchangeControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.stocksexchange.stockexchangeDTOs.AccountDTO;
import com.stocks.stocksexchange.stockexchangeServices.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/stocktrader")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String getMethodName() {
        return "new String()";
    }
    
    // Register a new trader and sending the account to other microservice
    @PostMapping("/new")
    public ResponseEntity<Void> registerTrader(@Valid @RequestBody AccountDTO accountDTO) {
        accountService.registerAccount(accountDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
