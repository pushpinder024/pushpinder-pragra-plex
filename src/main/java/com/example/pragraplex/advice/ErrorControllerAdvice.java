package com.example.pragraplex.advice;

import com.example.pragraplex.dto.ErrorDto;
import com.example.pragraplex.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ErrorControllerAdvice {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCustomerNotFound(CustomerNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder()
                .errorCode(1001)
                .message(ex.getMessage())
                .errorDate(new Date())
                .build());
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorDto> handleMovieNotFound(MovieNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder()
                .errorCode(1005)
                .message(ex.getMessage())
                .errorDate(new Date())
                .build());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDto> handleOrderNotFound(OrderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder()
                .errorCode(1010)
                .message(ex.getMessage())
                .errorDate(new Date())
                .build());
    }

    @ExceptionHandler(UnsupportedLoginNameException.class)
    public ResponseEntity<ErrorDto> handleUnsupportedLoginException(UnsupportedLoginNameException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder()
                .errorCode(1015)
                .message(ex.getMessage())
                .errorDate(new Date())
                .build());
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<ErrorDto> handleEmailAlreadyInUse(EmailAlreadyInUseException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorDto.builder()
                .errorCode(1020)
                .message(ex.getMessage())
                .errorDate(new Date())
                .build());
    }
}
