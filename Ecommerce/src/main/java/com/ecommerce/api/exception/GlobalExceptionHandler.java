package com.ecommerce.api.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDetails> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), new Date(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ErrorDetails> handleJwtException(JwtException exception, WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), new Date(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), new Date(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request){
		Map<String, Object> result = new HashMap<>();
		
		exception.getBindingResult().getAllErrors().forEach(error->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			result.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,Object>>(result,HttpStatus.BAD_REQUEST);
	}
}











