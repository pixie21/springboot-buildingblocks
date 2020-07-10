package com.stacksimplify.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.Entities.Views;
import com.stacksimplify.restservices.Exceptions.UserNotFound;
import com.stacksimplify.restservices.Services.UserServices;

@RestController
@Validated
@RequestMapping(value="/jsonviews/users")
public class UserJsonViewController {
	
	@Autowired
	private UserServices userServices;
	
	//get user by id-External
	
	@JsonView(Views.External.class)
		@GetMapping("/external/{id}")
		public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
			try {
				return userServices.getUserById(id);
			}
			catch(UserNotFound ex){
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
				
			}
		}

		//get user by id
		@GetMapping("/internal/{id}")
		@JsonView(Views.Internal.class)
		public Optional<User> getUserById2(@PathVariable("id") @Min(1) Long id){
			try {
				return userServices.getUserById(id);
			}
			catch(UserNotFound ex){
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
				
			}
		}
}
