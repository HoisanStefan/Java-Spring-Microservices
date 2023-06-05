package com.awbd2.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {

    private long cardId;

    private String cardNumber;

    private String cvv;

}
