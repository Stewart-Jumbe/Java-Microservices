package com.stewartjumbe.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
//@REstController notation makes into a controller that can handle rest requests
@RestController 
public class HelloWorldController {
	
	//GET
	//URI - /hellow-world
	//method - "Hello Word"
	@RequestMapping(method = RequestMethod.GET, path ="/hello-world")
	public String helloWorld() {
		return "Hello World is working";
	}
}
