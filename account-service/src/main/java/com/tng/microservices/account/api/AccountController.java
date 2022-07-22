package com.tng.microservices.account.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.tng.microservices.account.exceptions.AccountNotFoundException;
import com.tng.microservices.account.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class AccountController {

    private List<Account> accounts;

    public AccountController() {
        accounts = new ArrayList<>();
        accounts.add(new Account(1, "CUST01", "111111"));
        accounts.add(new Account(2, "CUST01", "222222"));
        accounts.add(new Account(3, "CUST02", "333333"));
        accounts.add(new Account(4, "CUST02", "444444"));
        accounts.add(new Account(5, "CUST03", "555555"));
        accounts.add(new Account(6, "CUST03", "666666"));
        accounts.add(new Account(7, "CUST04","77777"));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/GetAllAccounts")
    public List<Account> findAll() {
        return accounts;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Account findById(@PathVariable Integer id) throws AccountNotFoundException {
       
        return accounts.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("id : " + id));
    }

   
    @RequestMapping(method = RequestMethod.GET, value = "/customer/{customer}")
    public List<Account> findAccountByCustomer(@PathVariable("customer") String customerId) {
        
        return accounts.stream()
                .filter(it -> it.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

 

}
