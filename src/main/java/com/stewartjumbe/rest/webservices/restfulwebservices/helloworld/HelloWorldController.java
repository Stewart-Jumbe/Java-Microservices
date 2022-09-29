package com.stewartjumbe.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Controller
//@REstController notation makes into a controller that can handle rest requests
@RestController 
public class HelloWorldController {
	
	
	private MessageSource messageSource;
	//constructor injection
	public HelloWorldController(MessageSource messageSource) {
		 this.messageSource = messageSource;
	 }
	
	
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
	
	/**
	 * User must use Accept Language header (in Postman or Talend API) then specify which language they want the API to respond in
	 * @return Good morning Message in chosen language
	 */
	@GetMapping(path = "/hello-world-internationalised")
	public String helloWorldInternationalised() {
		Locale locale = LocaleContextHolder.getLocale();
		
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
	
	
}
