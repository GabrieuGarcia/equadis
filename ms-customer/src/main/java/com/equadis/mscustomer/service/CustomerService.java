package com.equadis.mscustomer.service;

import com.equadis.mscustomer.model.Customer;

import java.util.UUID;

public interface CustomerService {

    Customer save(Customer customer);

    Customer findById(UUID customerId);
}
