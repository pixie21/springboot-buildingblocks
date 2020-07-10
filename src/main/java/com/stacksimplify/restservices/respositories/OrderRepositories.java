package com.stacksimplify.restservices.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacksimplify.restservices.Entities.Order;

public interface OrderRepositories extends JpaRepository<Order, Long> {

}
