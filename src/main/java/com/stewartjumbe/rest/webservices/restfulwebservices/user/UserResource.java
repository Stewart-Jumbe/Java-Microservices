package com.stewartjumbe.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
	
	
	//injecting Beans needed to get data from 'db'
	@Autowired
	private UserDaoService service;
	
	
	//GET /users
	//retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		
		return service.findAll();
		
	}
	
	//Get /users/{id}
	//retrieveUser
	@GetMapping("/users/{id}")
	public User retrieveUSer(@PathVariable int id) {
		return service.findOne(id);
	}

	//input - details of user
	//output - Created & Return the created URI
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		//using @ReqestBody puts whatever is entered into the requestbody (via the url) as the input of the createUser method
		User savedUser= service.save(user);
		
	}

}
