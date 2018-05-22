package com.example.api.exception;

import org.springframework.validation.Errors;

public class InvalidRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 126962691914771844L;
	
	private Errors errors;

    public InvalidRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
