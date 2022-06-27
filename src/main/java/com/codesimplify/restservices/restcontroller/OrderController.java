package com.codesimplify.restservices.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codesimplify.restservices.entities.Order;
import com.codesimplify.restservices.entities.User;
import com.codesimplify.restservices.exceptions.UserNotFoundException;
import com.codesimplify.restservices.repository.OrderRepository;
import com.codesimplify.restservices.repository.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@GetMapping("/getAllorders/{userid}/orders")
	public List<Order>getAllOrders(@PathVariable Long userid) throws UserNotFoundException
	{
		Optional<User> user=userRepository.findById(userid);
		if(!user.isPresent())
		{
			throw new UserNotFoundException("no user found with userid !");
		}
		return user.get().getOrders();
	}
	@PostMapping("/createOrder/{userid}/orders")
	public Order createOrder(@PathVariable Long userid,@RequestBody Order order) throws UserNotFoundException
	{
		Optional<User> optionalUser=  userRepository.findById(userid);
		if(!optionalUser.isPresent())
		{
			throw new UserNotFoundException("User not found to create order");
			
		}
		User user=optionalUser.get();
		order.setUser(user);
		return orderRepository.save(order);
		
	}
}
