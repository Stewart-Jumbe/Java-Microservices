package com.stewartjumbe.rest.webservices.restfulwebservices.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This class defined the structure of the errors that users will encounter 
 * (i.e what standard information will be presented)
 * @author stewart.jumbe
 *
 */
public class ErrorDetails {

	private LocalDateTime timestamp;
	private String message;
	private String details;
	
	
	public ErrorDetails(LocalDateTime timestamp,String message,String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public String getMessage() {
		return message;
	}


	public String getDetails() {
		return details;
	}
	

	
	
}
