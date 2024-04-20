package com.vivian.World.Banking.App.infrastructure.exception;

public class EmailNotSentException extends RuntimeException{
    public EmailNotSentException(String message) {
        super(message);
    }
}
