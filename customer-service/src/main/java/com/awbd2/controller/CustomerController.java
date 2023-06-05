package com.awbd2.controller;

import com.awbd2.request.CreateCustomerRequest;
import com.awbd2.response.CardResponse;
import com.awbd2.response.CustomerResponse;
import com.awbd2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        CustomerResponse customerResponse = customerService.createCustomer(createCustomerRequest);
        customerResponse.add(linkTo(methodOn(CustomerController.class).getById(customerResponse.getCustomerId())).withSelfRel());

        return customerResponse;
    }

    @GetMapping("/getById/{id}")
    public CustomerResponse getById(@PathVariable long id) {
        CustomerResponse customerResponse = customerService.getById(id);
        customerResponse.add(linkTo(methodOn(CustomerController.class).getById(customerResponse.getCustomerId())).withSelfRel());

        return customerResponse;
    }
}
