package com.awbd2.response;

import com.awbd2.entity.Card;
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

    public CardResponse(Card card) {
        this.cardId = card.getId();
        this.cardNumber = card.getCardNumber();
        this.cvv = card.getCvv();
    }

}
