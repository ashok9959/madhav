package com.ashok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashok.entity.Role;
import com.ashok.entity.UserGroup;
import com.ashok.exception.BusinessValidationException;
import com.ashok.repository.UserGroupRepository;

@Service
public class UserGroupService {
	@Autowired
	UserGroupRepository userGroupRepository;
	
	@Autowired
	RoleService roleService;

public UserGroup addUserGroup(UserGroup userGroup) {
		//TODo check if specified user database,if it is there throw the exception 
	       UserGroup exestUserGroup = this.findUserGroup(userGroup.getUserGroupName());
	       if(exestUserGroup!=null) {
	    	   throw new BusinessValidationException("userGroup is already exist.");
	       }
	       
	     
	       
	     //TODO check user group is there are not, if its not there throw the exception
	       List<Role> roles = userGroup.getRoles();
	       for(Role r:roles) {
	    	   Role checkRole = roleService.findRole(r.getRoleName());
	    	   if(checkRole==null) {
	    		   throw new BusinessValidationException("specified role  is not availabel - "+r.getRoleName());
	    	   }
	    	   
	       }
	
		return userGroupRepository.save(userGroup);
	}

public UserGroup updateUserGroup(UserGroup userGroup) {
	
	//TODo check if specified user database,if it is not there throw the exception
	UserGroup existingUserGroup = this.findUserGroup(userGroup.getUserGroupName());
	if(existingUserGroup==null) {
		throw new BusinessValidationException("usergroup does not exist");
	}
	
	//TODO check the specified user already present in the database, if it present update after the validation, if not present throw the 
			//exception-usernotfoundexception
	
	List<Role> roless = userGroup.getRoles();
	for(Role rr:roless) {
		Role rol = roleService.findRole(rr.getRoleName());
		if(rol==null) {
			throw new BusinessValidationException("specified role  is not availabel -"+rr.getRoleName());
		}
	}
	
	return userGroupRepository.save(userGroup);
}

public List<UserGroup> getAllUserGroup( ) {
	
	return userGroupRepository.findAll();
}

public UserGroup findUserGroup(String name) {
	
	return userGroupRepository.findByuserGroupName(name);
}

}
