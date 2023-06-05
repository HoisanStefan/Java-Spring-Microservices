package com.awbd2.response;

import com.awbd2.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private long customerId;

    private String firstName;

    private String lastName;

    private CardResponse cardResponse;

    public CustomerResponse(Customer customer) {
        this.customerId = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
    }

}
