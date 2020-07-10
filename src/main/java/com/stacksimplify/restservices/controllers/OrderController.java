package com.stacksimplify.restservices.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.Entities.Order;
import com.stacksimplify.restservices.Entities.User;
import com.stacksimplify.restservices.Exceptions.CustomErrorDetails;
import com.stacksimplify.restservices.Exceptions.UserExistsException;
import com.stacksimplify.restservices.Exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.Exceptions.UserNotFound;
import com.stacksimplify.restservices.respositories.OrderRepositories;
import com.stacksimplify.restservices.respositories.UserRepositories;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	@Autowired
	private UserRepositories userRepositories;
	
	@Autowired
	private OrderRepositories orderRepositories;
	
	
	//get all orders method
		@GetMapping("/{userid}/orders")
		public List<Order> getAllOrders(@PathVariable Long userid) throws UserNameNotFoundException {
			Optional<User> user = userRepositories.findById(userid);
			if(!user.isPresent()) {
				throw new UserNameNotFoundException("User not found");
			}
			return user.get().getOrders();
		}
		
		//create orders
		@PostMapping("/{userid}/orders")
		public Order createOrder(@PathVariable Long userid, @RequestBody Order order ) throws UserNotFound {
			Optional<User> user = userRepositories.findById(userid);
			if(!user.isPresent()) {
				throw new UserNotFound("User not found");
			}
			User user2 = user.get();
			order.setUser(user2);
				
			return orderRepositories.save(order);
			
			
		}
		
		
		//get order by id
		
		@GetMapping("/{userid}/orders/{orderid}")
		public Optional<Order> getOrderById(@PathVariable @Min(1) Long userid, @PathVariable Long orderid) throws UserNotFound, UserNameNotFoundException{
			Optional<User> user = userRepositories.findById(userid);
			if(!user.isPresent()) {
				throw new UserNameNotFoundException("User not found");
			}
			List<Order> ordersList = user.get().getOrders();
			
			Optional<Order> order = orderRepositories.findById(orderid);
			
			//Another way it can be done
//			for (Order orderdet: ordersList) {
//				if (orderdet.getOrderidLong().equals(orderid)){
//					return orderdet;
//				}
//			}
//				throw new UserNotFound("Order Not found");
			if(!order.isPresent()) {
				throw new UserNameNotFoundException("Order not found");
			}
			return order;
			
		}
		

}
