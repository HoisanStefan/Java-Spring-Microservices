package com.awbd2.exceptions;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
