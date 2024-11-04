package com.ashok.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashok.entity.User;
import com.ashok.exception.UserNotFoundException;
import com.ashok.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	private Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public User addUser(@Valid @RequestBody User user) {
		User savedUser = userService.addUser(user);
		//if(savedUser==null) {
		logger.info("user {} is created sucessfully", user.getUserName());
		//throw new UserNotFoundException("user with "+ user +"not found");
		//}
		return savedUser;
		
	}
	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
		
	}
	
	@GetMapping("/user")
	public List<User> getAllUser(){
		return userService.getAllUser();
		
	}
	
	@GetMapping("/user/{name}")
	public User findUser(@PathVariable String name){
		User users = userService.findUser(name);
		if(users ==null) {
			logger.error("User with name {} not found",name);
			throw new UserNotFoundException("User with name " + name + " not found");
		}
		return users;
		
	}
	@GetMapping("/user")
	public List<User> getUsers( @RequestParam(defaultValue = "0") int size,  @RequestParam(defaultValue = "10") int pageNo,@RequestParam String sort, @RequestParam(defaultValue = "asc") String sortOrder){
		return getUsers(size,pageNo,sort,sortOrder);
		
	}
	

}
