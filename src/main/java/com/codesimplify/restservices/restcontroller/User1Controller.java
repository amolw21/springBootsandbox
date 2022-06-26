package com.codesimplify.restservices.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codesimplify.restservices.entities.User;
import com.codesimplify.restservices.service.UserService;

@RestController
public class User1Controller {
	@Autowired
	private UserService userService;
	@GetMapping("/getallusers")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	@PostMapping("/createuser1")
	public User createUser(@RequestBody User user)
	{
		return userService.createUser(user);
	}
	@GetMapping("/getuser/{userid}")
	public Optional<User> getUserById(@PathVariable("userid") Long id)
	{
		return userService.getUserById(id);
	}
	@PutMapping("/updateuser/{userid}")
	public User updateUserById(@RequestBody User user,@PathVariable("userid") Long id)
	{
		return userService.updateUserbyId(id,user);
	}
	@DeleteMapping("/deleteuser/{userid}")
	public void deleteUserById(@PathVariable("userid") Long id)
	{
		userService.deleteUserById(id);
	}
	@GetMapping("/getuser/byusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username)
	{
		return userService.getUserByUsername(username);
	}
}
