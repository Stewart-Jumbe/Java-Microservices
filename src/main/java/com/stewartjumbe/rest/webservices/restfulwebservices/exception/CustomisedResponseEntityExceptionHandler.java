package com.stewartjumbe.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.stewartjumbe.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice //this will now apply to all controllers
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)//defining what exceptions i want to handle...everything
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)//defining what exceptions i want to handle...everything
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
		
	}

}
