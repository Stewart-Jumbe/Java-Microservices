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

import com.stewartjumbe.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.stewartjumbe.rest.webservices.restfulwebservices.jpa.UserRepository;

@RestController
public class UserJpaResource {
	
	
	//injecting Beans needed to get data from 'db'
	//alternative way is using constructor injection : private UserDaoService service;
	//public UserResource(UserDaoService service){this.service = service}
	@Autowired
	private UserDaoService service;
	


	private UserRepository userRepository;
	private PostRepository postRepository;
	
	//injecting UserRepository into UserJpaResource constructor
	public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	
	//GET /users
	//retrieveAllUsers
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		
		return userRepository.findAll();
		
	}
	
	//Get /users/{id}
	//retrieveUser
	//note: implementing HATEOAS requires EntityModel and WebMvcLinkBuilder
	//With HATEOS implemented when a user get data by {id} a link will also be provided to get all user data
	@GetMapping("/jpa/users/{id}")
	public EntityModel<Optional<User>> retrieveUser(@PathVariable int id) {
		Optional<User> user =userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:" +id);
		
		EntityModel<Optional<User>> entityModel = EntityModel.of(user);
		
		 
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	//Getting a post
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> user =userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:" +id);
		
		return user.get().getPosts();
	}
	
	
	@GetMapping("/jpa/users/{id}/posts/{postID}")
	public Post retrievePostsForUserByPostID(@PathVariable int id, @PathVariable int postID) {
		Optional<User> user =userRepository.findById(id);
		Optional<Post> post =postRepository.findById(postID);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:" +id);
		if(post.isEmpty())
			throw new PostNotFoundException("id:" +postID);
		
		System.out.println("value of post is "+post.get().getDescription());
		System.out.println("value of postID is "+ postID);
		System.out.println("*****: "+ user.get().getPosts().get(postID));
		return post.get();
	}
	
	
	
	//Creating  a post
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		//Checking user ID exists
		Optional<User> user =userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:" +id);
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location =ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
	
		userRepository.deleteById(id);
		
	}

	//input - details of user
	//output - Created & Return the created URI
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		//using @ReqestBody puts whatever is entered into the requestbody (via the url) as the input of the createUser method
		
		
		
		User savedUser= userRepository.save(user);
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
