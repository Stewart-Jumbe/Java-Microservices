package com.stewartjumbe.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller
//@REstController notation makes into a controller that can handle rest requests
@RestController 
public class HelloWorldController {
	
	
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World is working";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
}
