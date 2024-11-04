package com.ashok.service;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ashok.entity.User;
import com.ashok.entity.UserGroup;
import com.ashok.exception.BusinessValidationException;
import com.ashok.repository.UserRepository;

public class UserServiceTest {
	@InjectMocks
	UserService userService = new UserService();
	@Mock
	UserRepository userRepository;
	@Mock
	UserGroupService userGroupService;

	@BeforeClass
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test(expectedExceptions = BusinessValidationException.class)
	public void addUserWhenUserAlreadyExist() {
		// userRepository=Mockito.mock(UserRepository.class);
		User user = new User();
		user.setUserName("user1");
		user.setDescription("user 1 desc");
		//mock the behaviour to return the existing user for user1
		//mackito means dumy implementation for the implementation
		Mockito.when(userRepository.findByuserName("user1")).thenReturn(user);

		userService.addUser(user);
	}

	@Test(expectedExceptions = BusinessValidationException.class)
	public void addUserWhenPwdAndCpwdNotEqual() {
		// userRepository=Mockito.mock(UserRepository.class);
		User user = new User();
		user.setUserName("user1");
		user.setPwd("Ashok@205");
		user.setCpwd("Ashok@123");
		user.setDescription("user 1 desc");
		Mockito.when(userRepository.findByuserName("user1")).thenReturn(null);

		userService.addUser(user);
	}

	@Test(expectedExceptions = BusinessValidationException.class)
	public void addUserWhenSpecifiedUserGroupDoesNotExist() {
		
		User user = new User();
		user.setUserName("user1");
		user.setDescription("user 1 desc");
		user.setPwd("Ashok@123");
		user.setCpwd("Ashok@123");
		UserGroup ug = new UserGroup();
		ug.setUserGroupName("ug1");
		List<UserGroup> userGroup = new ArrayList();
		userGroup.add(ug);
		user.setUserGroup(userGroup);
		Mockito.when(userRepository.findByuserName("user1")).thenReturn(null);
		Mockito.when(userGroupService.findUserGroup(ug.getUserGroupName())).thenReturn(null);

		userService.addUser(user);
	}
	@Test
	public void addUserWhenUserCreatedSuccessfully() {
		User user = new User();
		user.setUserName("user1");
		user.setDescription("user 1 desc");
		user.setPwd("Ashok@123");
		user.setCpwd("Ashok@123");
		UserGroup ug = new UserGroup();
		ug.setUserGroupName("ug1");
		List<UserGroup> userGroup = new ArrayList();
		userGroup.add(ug);
		user.setUserGroup(userGroup);
		Mockito.when(userRepository.findByuserName("user1")).thenReturn(null);
		Mockito.when(userGroupService.findUserGroup(ug.getUserGroupName())).thenReturn(ug);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		User createdUser = userService.addUser(user);
		Assert.assertNotNull(createdUser,"addUser is not created Successfully..");
	}

	@Test(expectedExceptions = BusinessValidationException.class)
	public void updateUserWhenUserDoesNotExist() {
		User user = new User();
		user.setUserName("user1");
		user.setDescription("user 1 desc");
		Mockito.when(userRepository.findByuserName("user1")).thenReturn(null);

		userService.updateUser(user);
	}
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void updateUserWhenPwdAndCpwdNotEqual() {
	
		User user = new User();
		user.setUserName("user1");
		user.setPwd("Ashok@205");
		user.setCpwd("Ashok@123");
		user.setDescription("user 1 desc");
		Mockito.when(userRepository.findByuserName("user1")).thenReturn(user);

		userService.updateUser(user);
	}
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void updateUserWhenSpecifiedUserGroupDoesNotExist() {
	
		User user = new User();
		user.setUserName("user1");
		user.setDescription("user 1 desc");
		user.setPwd("Ashok@123");
		user.setCpwd("Ashok@123");
		UserGroup ug = new UserGroup();
		ug.setUserGroupName("ug1");
		List<UserGroup> userGroup = new ArrayList();
		userGroup.add(ug);
		user.setUserGroup(userGroup);
		Mockito.when(userRepository.findByuserName("user1")).thenReturn(user);
		Mockito.when(userGroupService.findUserGroup(ug.getUserGroupName())).thenReturn(null);

		userService.updateUser(user);
	} 
	

@Test
public void updateUserWhenUserCreatedSuccessfully() {
	User user = new User();
	user.setUserName("user1");
	user.setDescription("user 1 desc");
	user.setPwd("Ashok@123");
	user.setCpwd("Ashok@123");
	UserGroup ug = new UserGroup();
	ug.setUserGroupName("ug1");
	List<UserGroup> userGroup = new ArrayList();
	userGroup.add(ug);
	user.setUserGroup(userGroup);
	Mockito.when(userRepository.findByuserName("user1")).thenReturn(user);
	Mockito.when(userGroupService.findUserGroup(ug.getUserGroupName())).thenReturn(ug);

	
	Mockito.when(userRepository.save(user)).thenReturn(user);
	User updateUser = userService.updateUser(user);
	Assert.assertNotNull(updateUser,"update User is not created Successfully..");
}

}
