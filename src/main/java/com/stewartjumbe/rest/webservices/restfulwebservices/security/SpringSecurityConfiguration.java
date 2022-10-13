package com.stewartjumbe.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//Spring security
//When you make a request to an API spring security intercepts it via filterChains
//filterChains are a series of checks that are done when you make a request (Get,Post etc)
//E.g. of what Filter Chains do:
//1) Check that all requests are authenticated
//2) If a request is not authenticated a web page is shown
//3) Enable CSRF check which means that POST and  PUT requests are not permitted


@Configuration
public class SpringSecurityConfiguration {

	//Using HTTP auth Basic Authentication
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Requiring all requests to be authenticated
		http.authorizeHttpRequests(
				auth->auth.anyRequest().authenticated());
		
		//Web page to be shown if a request is not authenticated
		http.httpBasic(withDefaults());
		
		//Disabling CSRF to allow post and put requests
		http.csrf().disable();
		
		return http.build();
	}

}
