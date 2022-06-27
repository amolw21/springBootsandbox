package com.codesimplify.restservices.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.codesimplify.restservices.entities.User;
import com.codesimplify.restservices.exceptions.UserExistsException;
import com.codesimplify.restservices.exceptions.UserNameNotFoundException;
import com.codesimplify.restservices.exceptions.UserNotFoundException;
import com.codesimplify.restservices.service.UserService;

@RestController
@Validated
public class User1Controller {
	@Autowired
	private UserService userService;
	@GetMapping("/getallusers")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}

	/*
	 * @PostMapping("/createuser1") public User createUser(@RequestBody User user) {
	 * // System.out.println(user); try { return userService.createUser(user); }
	 * catch (UserExistsException e) { // TODO Auto-generated catch block throw new
	 * ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage()); } }
	 */
	@PostMapping("/createuser1")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user,UriComponentsBuilder builder)
	{
//		System.out.println(user);
		try {
			 userService.createUser(user);
			 HttpHeaders headers=new HttpHeaders();
			 headers.setLocation(builder.path("/getuser/{userid}").buildAndExpand(user.getId()).toUri());
			 return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		} catch (UserExistsException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	@GetMapping("/getuser/{userid}")
	public Optional<User> getUserById(@PathVariable("userid") @Min(1) Long id)
	{
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	@PutMapping("/updateuser/{userid}")
	public User updateUserById(@RequestBody User user,@PathVariable("userid")  Long id)
	{
		try {
			return userService.updateUserbyId(id,user);
		} catch (UserNotFoundException e) {
			//e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	@DeleteMapping("/deleteuser/{userid}")
	public void deleteUserById(@PathVariable("userid") Long id)
	{
		try {
			userService.deleteUserById(id);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	@GetMapping("/getuser/byusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username) throws UserNameNotFoundException
	{
		User user=userService.getUserByUsername(username);
		if(user==null)
		{
			throw new UserNameNotFoundException("Username :'" +username+"' not Found");
		}
		return user;
		//return userService.getUserByUsername(username);
	}
}
