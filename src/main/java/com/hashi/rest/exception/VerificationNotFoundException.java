package com.hashi.rest.exception;

public class VerificationNotFoundException extends RuntimeException{
	private String message;
	
	public VerificationNotFoundException(String message) {
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
