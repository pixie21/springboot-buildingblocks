package com.stacksimplify.restservices.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.Exceptions.UserNotFound;
import com.stacksimplify.restservices.Services.UserServices;

@RestController
@RequestMapping(value="/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {
	
	//Autowire the UserService
		@Autowired
		
		private UserServices userService;
	
	//get user by id-- fields with hashset
		@GetMapping("/{id}")
		public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id){
			try {
				
				Optional<User> useroptional = userService.getUserById(id);
				User user = useroptional.get();
				
				MappingJacksonValue mapperJacksonValue = new MappingJacksonValue(user);
				Set<String> fields = new HashSet<String>();
				fields.add("userid");
				fields.add("username");
				fields.add("ssn");
				FilterProvider filters =  new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields )) ;
				mapperJacksonValue.setFilters(filters);
				return mapperJacksonValue;
			}
			catch(UserNotFound ex){
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
				
			}
		}

		

		//get user by id-- fields with params
			@GetMapping("/params/{id}")
			public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields){
				try {
					
					Optional<User> useroptional = userService.getUserById(id);
					User user = useroptional.get();
					
					MappingJacksonValue mapperJacksonValue = new MappingJacksonValue(user);
					
					FilterProvider filters =  new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields )) ;
					mapperJacksonValue.setFilters(filters);
					return mapperJacksonValue;
				}
				catch(UserNotFound ex){
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
					
				}
			}

}
