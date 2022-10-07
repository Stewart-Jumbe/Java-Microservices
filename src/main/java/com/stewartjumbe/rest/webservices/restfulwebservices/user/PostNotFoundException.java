package com.stewartjumbe.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {
	
	//accepts a message and passes it onto the runtime Exception (super class)

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public PostNotFoundException(String message) {
		super(message);
	}

}
