package com.hashi.rest.exception;

public class AlreadyVerifiedException extends RuntimeException{
	private String message;
	
	public AlreadyVerifiedException(String message) {
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
