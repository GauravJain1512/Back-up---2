package com.innovator.practice.learing.portal.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private String message;
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiException(HttpStatus status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}
	
	

}
