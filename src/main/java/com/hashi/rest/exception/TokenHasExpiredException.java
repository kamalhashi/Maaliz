package com.hashi.rest.exception;

public class TokenHasExpiredException extends RuntimeException{
	private String message;
	
	public TokenHasExpiredException(String message) {
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
