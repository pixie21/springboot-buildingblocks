package com.stacksimplify.restservices.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//entity
@Entity
@Table
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="User_name", length=50, nullable=false, unique=true)
	private String username;
	@Column(name="First_name", length=50, nullable=false, unique=false)
	private String firstname;
	@Column(name="last_name", length=50, nullable=false, unique=false)
	private String lastname;
	@Column(name="Email", length=50, nullable=false, unique=false)
	private String email;
	@Column(name="Role", length=50, nullable=false, unique=false)
	private String role;
	@Column(name="SSN", length=50, nullable=false, unique=true)
	private String ssn;
	
	
	//no Argument constructor
	public User() {

	}


	public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
	
	
	
	

}
