package com.qantas.api.customerapi.repository;

import com.qantas.api.customerapi.model.Customer;

import java.util.List;

public interface CustomerCrmRepository {
    Customer create(Customer customer);

    List<Customer> findAll();

    Customer getById(int id);

    Customer update(int id, Customer customer);

    void delete(int id);
}
