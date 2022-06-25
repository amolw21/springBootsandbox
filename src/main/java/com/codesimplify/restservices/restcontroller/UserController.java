package com.codesimplify.restservices.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@GetMapping("/welcome")
	public String welcomeUser()
	{
		return "welcome to RestUserController";
	}
}
