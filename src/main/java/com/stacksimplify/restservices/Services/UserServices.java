package com.stacksimplify.restservices.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.Exceptions.UserExistsException;
import com.stacksimplify.restservices.Exceptions.UserNotFound;
import com.stacksimplify.restservices.respositories.UserRepositories;

@Service
public class UserServices {
	//Autowire the UserRepository
	@Autowired
	
	private UserRepositories userRepository;
	
	//get All Users method
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//create user method
	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser !=null)
		{
			throw new UserExistsException("User already exists");
		}
		
		return userRepository.save(user);
	}
	
	//get user by id method
	public Optional<User> getUserById(Long id) throws UserNotFound {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFound("User Not found");
		}
		return user;
	}
	
	//update user by id
	
	public User updateUserById(Long id, User user) throws UserNotFound{
		Optional<User> optionaluser = userRepository.findById(id);
		if(!optionaluser.isPresent()) {
			throw new UserNotFound("User Not found, cannot update");
		}
		user.setId(id);
		return userRepository.save(user);
		
	}
	
	//deletUser
	
	public void deleteUserById(Long id) {
		Optional<User> optionaluser = userRepository.findById(id);
		if(!optionaluser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found, cannot be deleted");
		}
			userRepository.deleteById(id);
		
	}
	
	//get all users with similar username
	
	public List<User> getAllUserByUsername(String username){
		List<User> userlist = userRepository.findAll();
		List<User> updatedlist = new ArrayList<User>();
		for (User userdets: userlist) {
			if (userdets.getUsername().contains(username)){
				updatedlist.add(userdets);
			}
			
		}
		return userRepository.saveAll(updatedlist);
	}
	
	//get user by username 
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
		
		
	}
	

}
