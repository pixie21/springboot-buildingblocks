package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.Entities.Order;
import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.Exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.respositories.OrderRepositories;
import com.stacksimplify.restservices.respositories.UserRepositories;

@RestController
@RequestMapping(value="/hateoas/users")
public class OrderHateoasController {
	
	@Autowired
	private UserRepositories userRepositories;
	@Autowired
	private OrderRepositories orderRepositories;
	
	//get all orders method
			@GetMapping("/{userid}/orders")
			public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNameNotFoundException {
				Optional<User> user = userRepositories.findById(userid);
				if(!user.isPresent()) {
					throw new UserNameNotFoundException("User not found");
				}
				List<Order> allOrders= user.get().getOrders();
				CollectionModel<Order> finalResourcesEntityModel = CollectionModel.of(allOrders);
				return finalResourcesEntityModel;
			}
}
