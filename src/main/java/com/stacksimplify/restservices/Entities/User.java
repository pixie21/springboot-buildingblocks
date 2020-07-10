package com.stacksimplify.restservices.Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

 
//entity
@Entity
@Table
@JsonIgnoreProperties({"firstname", "lastname"})
public class User extends RepresentationModel {
	@Id
	@GeneratedValue
	private Long userid;
	
	
	@NotEmpty(message = "Username is Mandatory. Please provide same")
	@Column(name="User_name", length=50, nullable=false, unique=true)
	private String username;
	
	@Size(min=2, message="First name should have at least 2 characters")
	@Column(name="First_name", length=50, nullable=false, unique=false)
	private String firstname;
	
	@Size(min=2, message="Surname name should have at least 2 characters")
	@Column(name="last_name", length=50, nullable=false, unique=false)
	private String lastname;
	
	
	@Column(name="Email", length=50, nullable=false, unique=false)
	private String email;
	@Column(name="Role", length=50, nullable=false, unique=false)
	private String role;
	
	
	@Column(name="SSN", length=50, nullable=false, unique=true)
	@JsonIgnore
	private String ssn;
	
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	
	//no Argument constructor
	public User() {

	}


	public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		this.userid = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}


	


	public Long getUserid() {
		return userid;
	}


	public void setUserid(Long userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getSsn() {
		return ssn;
	}


	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders + "]";
	}



	
	
	
	
	

}
