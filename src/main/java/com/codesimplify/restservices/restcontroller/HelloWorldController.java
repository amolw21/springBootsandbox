package com.codesimplify.restservices.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codesimplify.restservices.entities.UserDetails;

@RestController
public class HelloWorldController {
	//@RequestMapping(method = RequestMethod.GET ,path = "/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld()
	{
		return "Hello World";
	}
	@GetMapping("/userdetails")
	public UserDetails getUserDetails()
	{
		return new UserDetails("tim", "hart","london");
	}
}
