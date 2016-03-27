package com.hashi.rest.exception;

public class ProductCreationException extends RuntimeException{
	private String message;
	
	public ProductCreationException(String message) {
        super(message);
        this.message = message;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
