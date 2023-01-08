package com.codesimplify.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.codesimplify.restservices.entity.User;
import com.codesimplify.restservices.exceptions.UserExistException;
import com.codesimplify.restservices.exceptions.UserNotFoundException;
import com.codesimplify.restservices.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	@PostMapping("/createUser")
	public ResponseEntity<Void> createUser(@RequestBody User user,UriComponentsBuilder builder)
	{
		try {
			userService.createUser(user);
			HttpHeaders headers=new HttpHeaders();
			headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		} catch (UserExistException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		//return user;
	}
	@GetMapping("/users/{id}")
	public Optional<User>getUserById(@PathVariable("id")Long id)
	{
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id,@RequestBody User user)
	{
		User updatedUser;
		try {
			updatedUser = userService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		return updatedUser;
		
	}
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Long id)
	{
		try {
			userService.deleteUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		
	}
	@GetMapping("/users/byusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username)
	{
		return userService.getUserByUserName(username);
	}
}
