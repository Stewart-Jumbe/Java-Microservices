package com.stewartjumbe.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties("password") // a way of applying static filtering to the whole class, but needs to be updated when field name is changed
public class UserDetails {
	
	private String name;
	private String email;
	
	@JsonIgnore//Static filtering applied so that response does not contain password. Static filtering on a bean is applied accross different REST API
	private String password;
	
	public UserDetails(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDetails [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	

	

}
