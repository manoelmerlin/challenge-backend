package com.backendchallenge.challenge.services.exceptions;

public class InvalidWithdraw extends RuntimeException {

    public InvalidWithdraw(String message) {
        super(message);
    }

    public InvalidWithdraw(String message, Throwable cause) {
        super(message, cause);
    }
}
