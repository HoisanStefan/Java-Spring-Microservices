package com.awbd2.service;

import com.awbd2.entity.Customer;
import com.awbd2.repository.CustomerRepository;
import com.awbd2.request.CreateCustomerRequest;
import com.awbd2.response.CardResponse;
import com.awbd2.response.CustomerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    private final WebClient webClient;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, WebClient webClient) {
        this.customerRepository = customerRepository;
        this.webClient = webClient;
    }

    public CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {

        Customer customer = new Customer();
        customer.setFirstName(createCustomerRequest.getFirstName());
        customer.setLastName(createCustomerRequest.getLastName());
        customer.setCardId(createCustomerRequest.getCardId());

        customerRepository.save(customer);

        CustomerResponse customerResponse = new CustomerResponse(customer);

        customerResponse.setCardResponse(getCardById(customer.getCardId()));

        return customerResponse;
    }

    public CustomerResponse getById(long id) {

        logger.info("Inside getById " + id);

        Customer customer = customerRepository.findById(id).get();

        CustomerResponse customerResponse = new CustomerResponse(customer);

        customerResponse.setCardResponse(getCardById(customer.getCardId()));

        return customerResponse;
    }

    public CardResponse getCardById(long cardId) {
        Mono<CardResponse> cardResponse =
                webClient.get().uri("/getById/" + cardId)
                        .retrieve().bodyToMono(CardResponse.class);

        return cardResponse.block();
    }

}
