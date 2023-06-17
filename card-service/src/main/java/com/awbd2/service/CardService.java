package com.awbd2.service;

import com.awbd2.entity.Card;
import com.awbd2.exceptions.CardNotFoundException;
import com.awbd2.repository.CardRepository;
import com.awbd2.request.CreateCardRequest;
import com.awbd2.response.CardResponse;
import com.awbd2.response.ExchangeRateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class CardService {

    Logger logger = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;
    private final WebClient webClient;
    private final Environment environment;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public CardService(CardRepository cardRepository, WebClient webClient, Environment environment) {
        this.cardRepository = cardRepository;
        this.webClient = webClient;
        this.environment = environment;
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

        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isEmpty()) {
            throw new CardNotFoundException("Card " + id + " not found!");
        }

        return new CardResponse(optionalCard.get());
    }

    public ExchangeRateResponse getExchangeRate() {
        System.out.println(environment.getProperty("exchangeApi.apiKey"));
        Mono<ExchangeRateResponse> addressResponse =
                webClient.get()
                        .uri("")
                        .header("X-Api-Key", environment.getProperty("exchangeApi.apiKey"))  // Set your API key here
                        .retrieve()
                        .bodyToMono(ExchangeRateResponse.class);

        return addressResponse.block();
    }
}
