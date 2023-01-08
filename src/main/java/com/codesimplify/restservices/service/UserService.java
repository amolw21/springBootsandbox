package com.codesimplify.restservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesimplify.restservices.entity.User;
import com.codesimplify.restservices.exceptions.UserExistException;
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

	public User createUser(User user) throws UserExistException{
		//if user already exist
		User existinguser=userRepository.findByUsername(user.getUsername());
		if(existinguser!=null)
		{
			throw new UserExistException("User Already exists in repository");
			
		}
		//if not exist throw UserExistsException
		
		return userRepository.save(user);
	}
	public Optional<User> getUserById(Long id)throws UserNotFoundException
	{
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent())
		{
			throw new UserNotFoundException("Userr not found in user Repository");
		}
		return user;
	}
	
	public User updateUserById(Long id,User user) throws UserNotFoundException
	{
		//User existinguser=userRepository.findById(id).get();
		Optional<User> existingUser=userRepository.findById(id);
		if(!existingUser.isPresent())
		{
			throw new UserNotFoundException("Userr not found in user Repository,provide the correct user id");
		}
			user.setId(id);
		return userRepository.save(user);
		
	}
	public void deleteUserById(Long id) throws UserNotFoundException
	{
		Optional<User> existingUser=userRepository.findById(id);
		if(!existingUser.isPresent())
		{
			throw new UserNotFoundException("Userr not found in user Repository,provide the correct user id");
		}
		
		userRepository.deleteById(id);
		/*
		 * if(userRepository.findById(id).isPresent()) { userRepository.deleteById(id);
		 * }
		 */	}
	
	public User getUserByUserName(String userName)
	{
		return userRepository.findByUsername(userName);
	}
}
