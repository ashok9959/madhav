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
import org.springframework.web.bind.annotation.RestController;

import com.ashok.entity.UserGroup;
import com.ashok.exception.UserNotFoundException;
import com.ashok.service.UserGroupService;

@RestController
public class UserGroupController {
	
	private Logger logger=LoggerFactory.getLogger(UserGroupController.class);
	
	@Autowired
	UserGroupService userGroupService;
	
	@PostMapping("/userGroup")
	public UserGroup addUserGroup(@RequestBody UserGroup userGroup) {
		return userGroupService.addUserGroup(userGroup);
		
	}
	
	@PutMapping("/userGroup")
	public UserGroup updateUserGroup(@RequestBody UserGroup userGroup) {
		return userGroupService.updateUserGroup(userGroup);
		
	}
	
	@GetMapping("/userGroup")
	public List<UserGroup> getAllUserGroup(){
		return userGroupService.getAllUserGroup();
		
	}
	
	@GetMapping("/userGroup/{name}")
	public UserGroup findUserGroup(@PathVariable String name){
		UserGroup userGroups = userGroupService.findUserGroup(name);
		if(userGroups==null ) {
			throw new UserNotFoundException("UserGroup with name " + name + " not found");
		}
		return userGroups;
		
	}
	
	

}
