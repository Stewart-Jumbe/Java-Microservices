package com.stewartjumbe.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserInfo {
	
	String name ;
	int age;
	@JsonIgnore//password info will not be provided when user retrieves info from bean
	String password;

	public UserInfo(String name, int age, String password) {
		this.name = name;
		this.age = age;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", age=" + age + ", password=" + password + "]";
	}
	
	
}
