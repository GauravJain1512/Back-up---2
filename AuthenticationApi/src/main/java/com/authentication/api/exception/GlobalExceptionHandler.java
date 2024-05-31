package com.authentication.api.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleUsernameNotFoundException(UsernameNotFoundException exception, WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), new Date(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ErrorDetails> handleJwtException(JwtException exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), new Date(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.FORBIDDEN);
	}

}
