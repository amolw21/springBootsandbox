package com.codesimplify.restservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesimplify.restservices.entity.User;
import com.codesimplify.restservices.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}

	public void createUser(User user) {
		userRepository.save(user);
	}
	public Optional<User> getUserById(Long id)
	{
		Optional<User> user=userRepository.findById(id);
		return user;
	}
	
	public User updateUserById(Long id,User user)
	{
		//User existinguser=userRepository.findById(id).get();
		
			user.setId(id);
		return userRepository.save(user);
		
	}
	public void deleteUserById(Long id)
	{
		if(userRepository.findById(id).isPresent())
		{
			userRepository.deleteById(id);
		}
	}
	
	public User getUserByUserName(String userName)
	{
		return userRepository.findByUsername(userName);
	}
}
