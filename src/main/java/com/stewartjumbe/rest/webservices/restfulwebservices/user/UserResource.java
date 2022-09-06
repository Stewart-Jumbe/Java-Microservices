package com.stewartjumbe.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		//using @ReqestBody puts whatever is entered into the requestbody (via the url) as the input of the createUser method
		User savedUser= service.save(user);
		
		//returning "Created" response
		
		//Getting the newly created user ID
		// users/{id} --> users/{savedUser.getId()
		URI location =ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

}
