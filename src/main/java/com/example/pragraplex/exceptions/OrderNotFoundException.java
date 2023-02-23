package com.example.pragraplex.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String format) {
        super(format);
    }
}
