package com.example.pragraplex.exceptions;

public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException(String format) {
        super(format);
    }
}
