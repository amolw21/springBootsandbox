package com.codesimplify.restservices.entities;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users" ,schema = "buildingblock")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty(message = "Username is Mandatory field,please provide username")
	@Column(name  ="user_name",unique=true,nullable=false,length=50)
	private String username;
	@Size(min = 2,message = "FirstName should have minimum 2 characters")
	@Column(name = "first_name",unique=true,nullable=false,length = 50)
	private String firstname;
	@Column(name = "last_name",nullable=false,length = 50)
	private String lastname;
	@Column(name = "email",length = 50,nullable=false)
	private String email;
	@Column(name = "role",length = 50,nullable = false)
	private String role;
	@Column(name = "ssn",nullable = false,unique = true,length = 50)
	private String ssn;
	
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public User() {
		super();
	}
	
	public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		super();
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
