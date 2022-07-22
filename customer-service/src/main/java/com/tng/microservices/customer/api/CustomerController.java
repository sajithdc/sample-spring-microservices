package com.tng.microservices.customer.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tng.microservices.customer.exceptions.CustomerNotFoundException;
import com.tng.microservices.customer.intercomm.AccountClient;
import com.tng.microservices.customer.model.Account;
import com.tng.microservices.customer.model.Customer;
import com.tng.microservices.customer.model.CustomerType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private AccountClient accountClient;

    private List<Customer> customers;

    public CustomerController() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "CUST01", "Anil", CustomerType.INDIVIDUAL));
        customers.add(new Customer(2, "CUST02", "Sachin", CustomerType.INDIVIDUAL));
        customers.add(new Customer(3, "CUST03", "Rahul", CustomerType.INDIVIDUAL));
        customers.add(new Customer(4, "CUST04", "Nikil", CustomerType.INDIVIDUAL));
    }

   
    @RequestMapping(method = RequestMethod.GET, value = "/GetAllCustomers")
    public List<Customer> findAll() {
        log.info("Customer.findAll()");
        return customers;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Customer findByCustomerId(@PathVariable("id") String id) throws CustomerNotFoundException {
        log.info(String.format("Customer.findById(%s)", id));
        Customer customer = customers.stream()
                .filter(it -> it.getCustomerId().equals(id))
                .findFirst().orElseThrow(() -> new CustomerNotFoundException("id : " + id));

        List<Account> accounts = accountClient.getAccounts(id);
        customer.setAccounts(accounts);
        return customer;
    }


 
}
