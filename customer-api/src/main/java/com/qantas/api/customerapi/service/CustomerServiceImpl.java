package com.qantas.api.customerapi.service;

import com.qantas.api.customerapi.model.Customer;
import com.qantas.api.customerapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer create(final Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void delete(long id) {
        customerRepository.delete(id);
    }

    @Override
    public Customer update(long id, Customer customer) {
        Customer customerExisting = customerRepository.findOne(id);
        customerExisting.setAddresses(customer.getAddresses());
        customerExisting.setFirstName(customer.getFirstName());
        customerExisting.setLastName(customer.getLastName());
        customerExisting.setDob(customer.getDob());
        return customerRepository.save(customerExisting);
    }
}
