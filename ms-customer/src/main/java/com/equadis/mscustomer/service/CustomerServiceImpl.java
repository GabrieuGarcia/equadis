package com.equadis.mscustomer.service;

import com.equadis.mscustomer.model.Customer;
import com.equadis.mscustomer.repository.CustomerRepository;
import com.equadis.mscustomer.utils.CustomerConverter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        var customerSaved = this.repository.save(CustomerConverter.modelToEntity(customer));
        return CustomerConverter.entityToModel(customerSaved);
    }

    @Override
    public Customer findById(UUID customerId) {
        var customer = this.repository.findById(customerId).get();
        return CustomerConverter.entityToModel(customer);
    }
}
