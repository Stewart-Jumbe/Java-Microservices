package com.stewartjumbe.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stewartjumbe.rest.webservices.restfulwebservices.user.Post;

public interface PostRepository extends JpaRepository<Post,Integer>{
	
	//Neccessary for crud functionaly using DB

}
