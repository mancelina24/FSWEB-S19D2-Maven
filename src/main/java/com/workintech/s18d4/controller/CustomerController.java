package com.workintech.s18d4.controller;


import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> findAll()
    {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id)
    {
        Customer customer = customerService.find(id);
        return new CustomerResponse(customer.getId(),customer.getEmail(),customer.getSalary());
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer)
    {
        Customer saved = customerService.save(customer);
        return new CustomerResponse(saved.getId(), saved.getEmail(), saved.getSalary());
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @RequestBody Customer customer)
    {
        Customer updatedCustomer = customerService.find(id);
        updatedCustomer.setId(customer.getId());
        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setAddress(customer.getAddress());
        updatedCustomer.setSalary(customer.getSalary());
        updatedCustomer.setAccounts(customer.getAccounts());
        customerService.save(updatedCustomer);
        return new CustomerResponse(updatedCustomer.getId(), updatedCustomer.getEmail(), updatedCustomer.getSalary());
    }

    @DeleteMapping("/{id}")
    public CustomerResponse delete(@PathVariable Long id)
    {
        Customer customer = customerService.delete(id);
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());
    }
}
