package com.tng.microservices.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Integer id;
    private String customerId;
    private String name;
    private CustomerType type;
    private List<Account> accounts;

    public Customer(int id, String customerId, String name, CustomerType type) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.type = type;
    }
}
