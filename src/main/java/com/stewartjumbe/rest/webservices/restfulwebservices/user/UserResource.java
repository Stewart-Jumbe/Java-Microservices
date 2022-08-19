package com.stewartjumbe.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
	
	
	//injecting Beans needed to get data from 'db'
	@Autowired
	private UserDaoService service;
	
	
	//GET /users
	//retrieveAllUsers
	public List<User> retrieveAllUsers(){
		
		return service.findAll();
		
	}
	
	//Get /users/{id}
	//retrieveUser

}
