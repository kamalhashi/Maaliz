package com.hashi.rest.exception;

import javax.mail.MessagingException;

public class CustomMessagingException extends RuntimeException{
	private String message;
	
	public CustomMessagingException(String message) {
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
