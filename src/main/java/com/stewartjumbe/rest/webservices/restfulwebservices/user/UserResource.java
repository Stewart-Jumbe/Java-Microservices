package com.stewartjumbe.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	
	//injecting Beans needed to get data from 'db'
	//alternative way is using constructor injection : private UserDaoService service;
	//public UserResource(UserDaoService service){this.service = service}
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
	public User retrieveUser(@PathVariable int id) {
		User user =service.findOne(id);
		
		if(user ==null)
			throw new UserNotFoundException("id:" +id);
		
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
	
		service.deleteById(id);
		
	}

	//input - details of user
	//output - Created & Return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		//using @ReqestBody puts whatever is entered into the requestbody (via the url) as the input of the createUser method
		
		
		
		User savedUser= service.save(user);
		//Getting the newly created user URI e.g "user/4", this can be used to verify the existence of the record with a Get request
		// users/{id} --> users/{savedUser.getId()
		//location = users/4
		URI location =ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		//returning "Created" response
		return ResponseEntity.created(location).build();
		
	}

}
