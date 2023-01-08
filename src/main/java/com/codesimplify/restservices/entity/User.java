package com.codesimplify.restservices.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "user_name",length=100,nullable=false,unique = true)
	private String username;
	
	@Column(name = "first_name",length=100,nullable = false,unique = true)
	private String firstname;
	
	@Column(name = "last_name",length=100,nullable = false,unique = true)
	private String lastname;
	
	@Column(name = "email",length=100,nullable = false,unique = true)
	private String email;
	
	@Column(name = "role",length=100,nullable = false,unique = false)
	private String role;
	
	@Column(name = "ssn",length=100,nullable = false,unique = true)
	private String ssn;
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
}
