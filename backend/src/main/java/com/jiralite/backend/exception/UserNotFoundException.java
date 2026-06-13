package com.jiralite.backend.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApiException {

    public UserNotFoundException(String email) {
        super("User not found: " + email, HttpStatus.NOT_FOUND);
    }
    
}
