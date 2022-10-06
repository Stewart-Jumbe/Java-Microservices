package com.stewartjumbe.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//To access hibernate DB use: localhost:8080/h2-console
@Entity(name ="user_info")
public class User {
	

	
	//Attributes
	
	@Id
	@GeneratedValue
	private Integer id;
	
	
	@Size(min=2, message = "Name should have at least 2 characters") //minimum of 2 characters for name
	@JsonProperty("user_name") //customising the field name in the response
	private String name;
	
	@Past(message = "Birth Date should be in the past") // birthDate cannot be a future date
	@JsonProperty("DOB")
	private Date birthDate;
	
	@OneToMany(mappedBy="user") //because one user can have many posts, the user is the field in post that owns the oneToMany relationship
	@JsonIgnore // I don't want this to be part of the response for the user bean
	private List<Post> posts;

	
	//Constructor
	
	protected User() {} //default constructor 

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	
	
	
	//Methods,Getters and Setters
	
	
	
	public Integer getId() {
		return id;
	}

	

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	

}
