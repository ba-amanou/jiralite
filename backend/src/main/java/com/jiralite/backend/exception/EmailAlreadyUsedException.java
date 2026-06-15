package com.jiralite.backend.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyUsedException extends ApiException {

    public EmailAlreadyUsedException(String email) {
        super("Email already in use: " + email, HttpStatus.CONFLICT);
    }
    
}
