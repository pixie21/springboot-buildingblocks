package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.Entities.Order;
import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.Exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.Exceptions.UserNotFound;
import com.stacksimplify.restservices.Services.UserServices;
import com.stacksimplify.restservices.respositories.UserRepositories;

import net.bytebuddy.asm.Advice.This;

@RestController
@RequestMapping(value="/hateoas/users")
public class UserHateoasController {
	@Autowired
	private UserRepositories userRepositories;
	
	@Autowired
	private UserServices userService;
	
	//get user by id
		@GetMapping("/{id}")
		public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id){
			try {
				Optional<User> optionaluser = userService.getUserById(id);
				User user = optionaluser.get();
				Long userid = user.getUserid();
				Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
				user.add(selflink);
				EntityModel<User> finalResource = EntityModel.of(user);
				
				return finalResource;
			}
			catch(UserNotFound ex){
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
				
			}
		}
		
		//get allusers method
		@GetMapping
		public CollectionModel<User> getAllUsers() throws UserNameNotFoundException{
			
			List<User> allusersList= userService.getAllUsers();
			
			for (User user: allusersList) {
				//Self link
				Long useridLong = user.getUserid();
				Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(useridLong).withSelfRel();
				user.add(selfLink);
				
				//Relationship link with all orders
				CollectionModel<Order> ordersCollectionModel = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(useridLong);
				Link ordersLink = WebMvcLinkBuilder.linkTo(ordersCollectionModel).withRel("all-orders");
				user.add(ordersLink);
			
			}
			//self link
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
			CollectionModel<User> finalResource = CollectionModel.of(allusersList, selfLink);
			return finalResource;
		}

}
