package com.ashok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ashok.entity.User;
import com.ashok.entity.UserGroup;
import com.ashok.exception.BusinessValidationException;
import com.ashok.repository.UserRepository;

import jakarta.validation.ValidationException;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserGroupService userGroupService;

	public User addUser(User user) {
		//TODo check if specified user database,if it is there throw the exception 
		User existingUser = this.findUser(user.getUserName());
		if(existingUser !=null) {
			throw new BusinessValidationException("user already exist ");
		} 
		//TODO explicit validation for pwd and cpwd same
		if(!user.getPwd().equals(user.getCpwd())) {
			throw new BusinessValidationException("cwp and cpwd are not same ");
		}
		//TODO check user group is there are not, if its not there throw the exception
		List<UserGroup> groups = user.getUserGroup();
		for(UserGroup ug: groups) {
			UserGroup userGroup = userGroupService.findUserGroup(ug.getUserGroupName());
			if(userGroup==null ) {
				throw new BusinessValidationException("specified usergroup  is not availabel ! "+ ug.getUserGroupName());
			}
		}
		
		return userRepository.save(user);
	}

	public User updateUser(User user) {
		//TODo check if specified user database,if it is not there throw the exception
		User existingUser = this.findUser(user.getUserName());
		if(existingUser ==null) {
			throw new BusinessValidationException("user does not exist ");
		}
		//TODO check the specified user already present in the database, if it present update after the validation, if not present throw the 
		//exception-usernotfoundexception
		if(!user.getPwd().equals(user.getCpwd())) {
			throw new BusinessValidationException("cwp and cpwd is not ");
		}
		List<UserGroup> groups = user.getUserGroup();
		for(UserGroup ug: groups) {
			UserGroup userGroup = userGroupService.findUserGroup(ug.getUserGroupName());
			if(userGroup==null ) {
				throw new BusinessValidationException("specified usergroup  is not availabel ! "+ ug.getUserGroupName());
			}
		}
		return userRepository.save(user);
	}
	
	

	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	public User findUser(String name) {
		
		return userRepository.findByuserName(name);
	}
	
	public Page<User> getUsers(int size, int pageNo, String sort, String sortOrder) {
        Sort sort1 = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(size, pageNo, sort1);
        return userRepository.findAll(pageable);
    }


}
