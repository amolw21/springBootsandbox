package com.codesimplify.restservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesimplify.restservices.entities.User;
import com.codesimplify.restservices.exceptions.UserExistsException;
import com.codesimplify.restservices.exceptions.UserNotFoundException;
import com.codesimplify.restservices.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	public User createUser(User user)throws UserExistsException
	{
		//System.out.println(user.getUsername());
		User existingUser=userRepository.findByUsername(user.getUsername());
		if(existingUser!=null)
		{
			throw new UserExistsException("User already Exists !");
		}
		return userRepository.save(user);
	}
	public Optional<User> getUserById(Long id) throws UserNotFoundException
	{
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent())
		{
			throw new UserNotFoundException("User not found provide correct user ID");
		}
		return user;		
	}
	public User updateUserbyId(Long id,User user) throws UserNotFoundException
	{
		Optional<User> optionalUser=userRepository.findById(id);
		if(!optionalUser.isPresent())
		{
			throw new UserNotFoundException("user not found to update,provide correct user ID");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id)throws UserNotFoundException
	{
		Optional<User> optionalUser=userRepository.findById(id);
		if(!optionalUser.isPresent())
		{
			throw new UserNotFoundException("user not found to Delete,provide correct user ID");
		}
		userRepository.deleteById(id);
	}
	public User getUserByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
}
