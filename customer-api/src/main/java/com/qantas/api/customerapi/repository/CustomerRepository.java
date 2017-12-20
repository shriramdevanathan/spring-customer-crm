package com.qantas.api.customerapi.repository;

import com.qantas.api.customerapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
