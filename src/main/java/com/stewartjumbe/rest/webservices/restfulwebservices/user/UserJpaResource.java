package com.stewartjumbe.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stewartjumbe.rest.webservices.restfulwebservices.jpa.UserRepository;

@RestController
public class UserJpaResource {
	
	
	//injecting Beans needed to get data from 'db'
	//alternative way is using constructor injection : private UserDaoService service;
	//public UserResource(UserDaoService service){this.service = service}
	@Autowired
	private UserDaoService service;
	


	private UserRepository repository;
	
	//injecting UserRepository into UserJpaResource constructor
	public UserJpaResource(UserRepository repository) {
		this.repository = repository;
	}
	
	
	//GET /users
	//retrieveAllUsers
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		
		return repository.findAll();
		
	}
	
	//Get /users/{id}
	//retrieveUser
	//note: implementing HATEOAS requires EntityModel and WebMvcLinkBuilder
	//With HATEOS implemented when a user get data by {id} a link will also be provided to get all user data
	@GetMapping("/jpa/users/{id}")
	public EntityModel<Optional<User>> retrieveUser(@PathVariable int id) {
		Optional<User> user =repository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:" +id);
		
		EntityModel<Optional<User>> entityModel = EntityModel.of(user);
		
		 
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
	
		repository.deleteById(id);
		
	}

	//input - details of user
	//output - Created & Return the created URI
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		//using @ReqestBody puts whatever is entered into the requestbody (via the url) as the input of the createUser method
		
		
		
		User savedUser= repository.save(user);
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
