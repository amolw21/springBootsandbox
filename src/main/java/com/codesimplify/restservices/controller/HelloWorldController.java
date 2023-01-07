package com.codesimplify.restservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codesimplify.restservices.entity.UserDetails;

@RestController
public class HelloWorldController {
	@GetMapping("/hello")
	public String HelloWorld()
	{
		return "welcome to buildingBlock";
	}
	@GetMapping("/userdetails")
	public UserDetails getUserDetails()
	{
		return new UserDetails("jerry","tiger","etenberg");
	}
}
