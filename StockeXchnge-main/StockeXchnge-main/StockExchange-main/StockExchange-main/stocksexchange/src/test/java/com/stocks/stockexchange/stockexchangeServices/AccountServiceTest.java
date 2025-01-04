package com.stocks.stockexchange.stockexchangeServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stocks.stocksexchange.stockexchangeDTOs.AccountDTO;
import com.stocks.stocksexchange.stockexchangeEntities.Account;
import com.stocks.stocksexchange.stockexchangeRepositories.AccountRepo;
import com.stocks.stocksexchange.stockexchangeServices.AccountService;

public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepo accountRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAccounts() {
        Account account1 = new Account();
        account1.setAccountId("1");
        account1.setFname("John");
        account1.setLname("Doe");
        account1.setEmail("john.doe@cognizant.com");
        account1.setStatus(true);

        when(accountRepo.findAll()).thenReturn(Arrays.asList(account1));

        List<AccountDTO> accounts = accountService.getAllAccounts();
        assertEquals(1, accounts.size());
        assertEquals("John", accounts.get(0).getFname());
    }

    @Test
    public void testRegisterAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setFname("Jane");
        accountDTO.setLname("Doe");
        accountDTO.setEmail("jane.doe@cognizant.com");
        accountDTO.setStatus(true);

        Account account = new Account();
        account.setAccountId("2");
        account.setFname("Jane");
        account.setLname("Doe");
        account.setEmail("jane.doe@cognizant.com");
        account.setStatus(true);

        when(accountRepo.save(any(Account.class))).thenReturn(account);

        AccountDTO savedAccount = accountService.registerAccount(accountDTO);
        assertEquals("Jane", savedAccount.getFname());
    }

    // Additional tests for updateAccount() can be added here
}
