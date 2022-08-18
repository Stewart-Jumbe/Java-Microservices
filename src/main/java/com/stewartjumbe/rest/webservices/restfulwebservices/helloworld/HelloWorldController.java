package com.stewartjumbe.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//hello-world/path-variable/{name}
	@GetMapping(path = "/hello-world/hello_to_name/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Hello World, %s",name));
	}
}
