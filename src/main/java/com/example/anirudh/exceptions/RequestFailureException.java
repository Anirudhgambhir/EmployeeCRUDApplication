package com.example.anirudh.exceptions;

public class RequestFailureException extends RuntimeException {
    public RequestFailureException(String message) {
        super(message);
    }
}
