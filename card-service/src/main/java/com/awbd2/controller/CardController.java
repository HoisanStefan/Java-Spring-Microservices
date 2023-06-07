package com.awbd2.controller;

import com.awbd2.response.ExchangeRateResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awbd2.request.CreateCardRequest;
import com.awbd2.response.CardResponse;
import com.awbd2.service.CardService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private final CardService cardService;
    private final Environment environment;

    @Autowired
    public CardController(CardService cardService, Environment environment) {
        this.cardService = cardService;
        this.environment = environment;
    }

    @PostMapping("/create")
    public CardResponse createCard(@RequestBody @Valid CreateCardRequest createCardRequest) {
        CardResponse cardResponse = cardService.createCard(createCardRequest);
        cardResponse.add(linkTo(methodOn(CardController.class).getById(cardResponse.getCardId())).withSelfRel());

        return cardResponse;
    }

    @GetMapping("/getById/{id}")
    public CardResponse getById(@PathVariable long id) {
        CardResponse cardResponse = cardService.getById(id);
        logger.info(environment.getProperty("info.app.version"));
        cardResponse.add(linkTo(methodOn(CardController.class).getById(cardResponse.getCardId())).withSelfRel());

        return cardResponse;
    }

}
