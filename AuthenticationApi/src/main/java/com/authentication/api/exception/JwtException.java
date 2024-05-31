package com.authentication.api.exception;

public class JwtException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public JwtException() {
		super();
	}

	public JwtException(String message) {
		super(message);
		this.message = message;
	}
	
	
	

}
