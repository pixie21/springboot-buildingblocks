package com.stacksimplify.restservices.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservices.Entities.User;

//Repository
@Repository
public interface UserRepositories extends JpaRepository<User, Long>{
	
	User findByUsername(String username);

}
