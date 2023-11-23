package com.example.anirudh.Exceptions;

public class RequestFailureException extends RuntimeException {
    public RequestFailureException(String message) {
        super(message);
    }
}
