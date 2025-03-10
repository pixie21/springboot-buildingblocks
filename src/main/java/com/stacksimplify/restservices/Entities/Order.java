package com.stacksimplify.restservices.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name="orders")
public class Order extends RepresentationModel {
	
	@Id
	@GeneratedValue
	@JsonView(Views.Internal.class)
	private Long orderidLong;
	
	@JsonView(Views.Internal.class)
	private String orderdescription;
	
	//many to one annotation
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getOrderidLong() {
		return orderidLong;
	}

	public void setOrderidLong(Long orderidLong) {
		this.orderidLong = orderidLong;
	}

	public String getOrderdescription() {
		return orderdescription;
	}

	public void setOrderdescription(String orderdescription) {
		this.orderdescription = orderdescription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
