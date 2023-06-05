package com.awbd2.controller;

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

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/create")
    public CardResponse createCard(@RequestBody CreateCardRequest createCardRequest) {
        CardResponse cardResponse = cardService.createCard(createCardRequest);
        cardResponse.add(linkTo(methodOn(CardController.class).getById(cardResponse.getCardId())).withSelfRel());

        return cardResponse;
    }

    @GetMapping("/getById/{id}")
    public CardResponse getById(@PathVariable long id) {
        CardResponse cardResponse = cardService.getById(id);
        cardResponse.add(linkTo(methodOn(CardController.class).getById(cardResponse.getCardId())).withSelfRel());

        return cardResponse;
    }

}
