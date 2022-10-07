package com.stewartjumbe.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min= 10 ,max = 256)
	private String description;

	
	@ManyToOne(fetch=FetchType.LAZY)//i'ved used lazy as i dont want user details associated with the post to be provided when getting a post
	@JsonIgnore
	private User user;
	

	//Constructor
	public Post() {}
	
	public Post(String description){
		this.description = description;
		
	}
	

	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	};
	
	
	
	
	
	

}
