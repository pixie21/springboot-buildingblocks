package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.Services.UserServices;

//Controller
@RestController
public class UserController {
	
	//Autowire the UserService
	@Autowired
	
	private UserServices userService;
	
	//get allusers method
	@GetMapping("/users")
	public List<User> getAllUsers(){
		
		return userService.getAllUsers();
	}
	
	//create user
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	//get user by id
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		return userService.getUserById(id);
	}
	
	//update user by id
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
		return userService.updateUserById(id, user);
			
	}
	
	//delete user by id
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	//search for a user
	@GetMapping("/users/search/{username}")
	public List<User> getAllUserByUsername(@PathVariable("username") String username){
		
		return userService.getAllUserByUsername(username);
		
				}
	
	//get user by username 
	@GetMapping("/users/byusername/{username}")
		public User getUserByUsername(@PathVariable ("username") String username) {
			return userService.getUserByUsername(username);
			
			
		}
}

