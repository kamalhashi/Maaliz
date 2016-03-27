package com.hashi.rest.exception;

public class ApplicantExistException extends RuntimeException{
	private String message;
	
	public ApplicantExistException(String message) {
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
