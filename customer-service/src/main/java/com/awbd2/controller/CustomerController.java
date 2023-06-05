package com.awbd2.controller;

import com.awbd2.request.CreateCustomerRequest;
import com.awbd2.response.CustomerResponse;
import com.awbd2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public CustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }

    @GetMapping("/getById/{id}")
    public CustomerResponse getById(@PathVariable long id) {
        return customerService.getById(id);
    }

}
