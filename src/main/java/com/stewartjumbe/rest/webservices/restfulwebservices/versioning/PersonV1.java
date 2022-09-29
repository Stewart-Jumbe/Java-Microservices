package com.stewartjumbe.rest.webservices.restfulwebservices.versioning;

public class PersonV1 {

	
	private String name;
	
	public PersonV1(String name) {
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "PersonV1 [name " +name+ "]";
	}
	
	

}
