package com.stewartjumbe.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringExampleController {
	
	@GetMapping("/filtering") // localhost:8080/filtering
	public UserDetails filtering() {
		return new UserDetails("Name","Email","Password");
	}

	@GetMapping("/filtering-list")
	public List<UserDetails> filteringList(){
		return Arrays.asList(new UserDetails("Ann","Ann@gmail.com","Password12"), new UserDetails("Jon","SomeEmail@gmail.com","Alright12"));
	}
	
	
}
