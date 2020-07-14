package com.stacksimplify.restservices.controllers;


import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.dtos.UserDtoV1;
import com.stacksimplify.restservices.dtos.UserDtoV2;
import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.Exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.Exceptions.UserNotFound;
import com.stacksimplify.restservices.Services.UserServices;

@RestController
@RequestMapping("/versioning/uri/users")
public class UserUriVersioningController {
	
	@Autowired
	private UserServices userService;
	
	@Autowired
	private ModelMapper modelMapper;

	//URI based Versioning -  V1
	@GetMapping({"/v1.0/{id}", "/v1.1/{id}" })
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFound {

				 Optional<User> userOptional = userService.getUserById(id);
				
				if(!userOptional.isPresent()) {
					throw new UserNotFound("user not found");
				}
				
				User user = userOptional.get();
			
			UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
			return userDtoV1;
	
	}
	
	
	//URI based Versioning -  V2
	@GetMapping("/v2.0/{id}")
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFound {

				 Optional<User> userOptional = userService.getUserById(id);
				
				if(!userOptional.isPresent()) {
					throw new UserNotFound("user not found");
				}
				
				User user = userOptional.get();
			
			UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
			return userDtoV2;
	
	}
	
	
	
	
	
}