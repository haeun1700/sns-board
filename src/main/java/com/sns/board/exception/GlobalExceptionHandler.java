package com.sns.board.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientErrorException.class)
    public ResponseEntity<ClientErrorException> handleClientErrorException(ClientErrorException e){
        return new ResponseEntity<>(
                new ClientErrorException(e.getStatus(), e.getMessage()), e.getStatus());
    }
}
