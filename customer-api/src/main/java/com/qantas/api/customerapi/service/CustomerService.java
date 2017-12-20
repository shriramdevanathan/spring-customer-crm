package com.qantas.api.customerapi.service;
import com.qantas.api.customerapi.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);

    List<Customer> getAll();

    Customer getById(long id);

    Customer update(long id, Customer customer);

    void delete(long id);
}