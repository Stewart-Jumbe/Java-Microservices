package com.stewartjumbe.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	//Attributes
	private static List<User>users = new ArrayList<>();
	private static int userCount =0;
	
	
	
//	static {
//		users.add(new User(1,"Steve",new Date()));
//		users.add(new User(2,"Adam", new Date()));
//		users.add(new User(3,"Twena", new Date()));
//		
//	}
	
	static {
	addUser("Steve");
	addUser("Adam");
	addUser("Twena");
	addUser("Twena");
	}	
	//Methods
	
	
	public static String addUser(String userName){
		if(users.contains(userName)) {
			System.out.println("That user already exists please add a new user");}
		
			userCount++;//increment user Count by 1, userCount will also be used as an id, acting as primary key 
			
			users.add(new User(userCount,userName, new Date()));
			
			return userName + " has been added";
	
	}
	
	//find all users
public List<User> findAll(){
		
		return users;
	}
	
	//Save a user
public User save(User user){
	//if no id present current Id will be incremented by 1
		if(user.getId()==null) {
			user.setId(++userCount);
		}
	users.add(user);
	return user;
	
	}


//Find a user
public User findOne(int id){
	
	Predicate<? super User> predicate = user -> user.getId().equals(id);
	
	return users.stream().filter(predicate ).findFirst().get();
}



}
