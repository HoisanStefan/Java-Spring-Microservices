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
        return cardService.createCard(createCardRequest);
    }

    @GetMapping("/getById/{id}")
    public CardResponse getById(@PathVariable long id) {
        return cardService.getById(id);
    }

}
