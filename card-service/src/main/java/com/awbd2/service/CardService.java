package com.awbd2.service;

import com.awbd2.entity.Card;
import com.awbd2.repository.CardRepository;
import com.awbd2.request.CreateCardRequest;
import com.awbd2.response.CardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    Logger logger = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardResponse createCard(CreateCardRequest createCardRequest) {

        Card card = new Card();
        card.setCardNumber(createCardRequest.getCardNumber());
        card.setCvv(createCardRequest.getCvv());

        cardRepository.save(card);

        return new CardResponse(card);

    }

    public CardResponse getById (long id) {

        logger.info("Inside getById " + id);

        Card card = cardRepository.findById(id).get();

        return new CardResponse(card);
    }

}
