package com.qantas.api.customerapi.controller;

import com.qantas.api.customerapi.model.Customer;
import com.qantas.api.customerapi.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/customerapi")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @ApiOperation(value="Get customers by name")
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCustomers(){
        return customerService.getAll();
    }

    @ApiOperation(value="Get customer by customer ID")
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(@PathVariable long customerId) {
        return customerService.getById(customerId);
    }

    @ApiOperation(value="update customer")
    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.update(customer.getId(), customer);
    }

    @ApiOperation(value="create customer")
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public Customer createCustomer(@RequestBody Customer customer) {
        Customer customer1 =  customerService.create(customer);
        System.out.println("Shrirama first name"+customer1.getFirstName());
        return customer1;
    }

    @ApiOperation(value="delete customer")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable long id) {
        customerService.delete(id);
    }
}
