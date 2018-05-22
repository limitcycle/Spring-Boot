package com.example.api.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3897804814237395464L;

	public NotFoundException(String message) {
        super(message);
    }
}
