package com.stacksimplify.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.Exceptions.UserNotFound;
import com.stacksimplify.restservices.Services.UserServices;
import com.stacksimplify.restservices.dtos.UserMmDto;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

	//Autowire the UserService
		@Autowired
		
		private UserServices userService;
		
		@Autowired
		private ModelMapper modelMapper;
		
	//get user by id
		@GetMapping("/{id}")
		public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFound{
			Optional<User> useroptional = userService.getUserById(id);
			if (!useroptional.isPresent()) {
				throw new UserNotFound("user not found");
			}
				User user = useroptional.get();
				UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
				return userMmDto;
			}
		
}
