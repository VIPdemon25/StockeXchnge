package com.stocks.stocksexchange.stockexchangeServices;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

import com.stocks.stocksexchange.stockexchangeDTOs.AccountDTO;
import com.stocks.stocksexchange.stockexchangeEntities.Account;
import com.stocks.stocksexchange.stockexchangeEntities.Portfolio;
import com.stocks.stocksexchange.stockexchangeRepositories.AccountRepo;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ModelMapper modelMapper;

    // private final String url = "http://localhost:8080/api/accounts";

    // @Autowired
    // private RestTemplate restTemplate;


    public List<AccountDTO> getAllAccounts() {
        return accountRepo.findAll().stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());
    }

    @Autowired
    private PortfolioService portfolioService;

    public AccountDTO registerAccount(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
        account = accountRepo.save(account);
        
        Portfolio portfolio = new Portfolio();
        portfolio.setAccountId(account.getAccountId());
        portfolio.setAccount(account);
        portfolioService.addHolding(account.getAccountId(), portfolio);
        
        AccountDTO updatedAccountDTO = modelMapper.map(account, AccountDTO.class);
        
        // AccountDTO response = restTemplate.postForObject(url, updatedAccountDTO, AccountDTO.class);
        // System.out.println(response);
        
        return updatedAccountDTO;
    }

    public AccountDTO updateAccount(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
        Account updatedAccount = accountRepo.save(account);
        return modelMapper.map(updatedAccount, AccountDTO.class);
    }
}
