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
import com.stacksimplify.restservices.Exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.Exceptions.UserNotFound;
import com.stacksimplify.restservices.respositories.UserRepositories;

//Service
@Service
public class UserServices {

	// Autowire the UserRepository
	@Autowired
	private UserRepositories userRepository;

	// getAllUsers Method
	public List<User> getAllUsers() {

		return userRepository.findAll();

	}

	// CreateUser Method
	public User createUser(User user) throws UserExistsException{
		//if user exist using username
		User existingUser = userRepository.findByUsername(user.getUsername());
	
		//if not exists throw UserExistsException
		if(existingUser != null) {
			throw new UserExistsException("User already exists in repository");
		}
		
	
		return userRepository.save(user);
	}

	// getUserById
	public Optional<User> getUserById(Long id) throws UserNotFound {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFound("User Not found in user Repository");
		}

		return user;
	}

	// updateUserById
	public User updateUserById(Long id, User user) throws UserNotFound {
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent()) {
			throw new UserNotFound("User Not found in user Repository, provide the correct user id");
		}

		
		user.setUserid(id);;
		return userRepository.save(user);

	}

	// deleteUserById
	public void deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not found in user Repository, provide the correct user id");
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

	// getUserByUsername

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}