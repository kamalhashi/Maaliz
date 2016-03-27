package com.hashi.rest.exception;

public class EmailNotFoundException extends RuntimeException{
	private String message;
	
	public EmailNotFoundException(String message) {
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
