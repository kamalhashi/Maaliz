package com.hashi.rest.exception;

public class AuthenticationException extends RuntimeException{
	private String message;
	
	public AuthenticationException(){}
	public AuthenticationException(String message) {
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
