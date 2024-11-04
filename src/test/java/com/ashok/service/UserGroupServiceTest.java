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

import com.ashok.entity.Role;
import com.ashok.entity.UserGroup;
import com.ashok.exception.BusinessValidationException;
import com.ashok.repository.UserGroupRepository;

public class UserGroupServiceTest {
	@InjectMocks
	UserGroupService userGroupService=new UserGroupService();
	@Mock
	UserGroupRepository userGroupRepository;
	@Mock
	RoleService roleService;
	
	@BeforeClass
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void addUserGroupWhenUserGroupAlreadyExists() {
		
		UserGroup userGroup=new UserGroup();
		userGroup.setUserGroupName("userGroup1");
		Mockito.when(userGroupRepository.findByuserGroupName("userGroup1")).thenReturn(userGroup);
		userGroupService.addUserGroup(userGroup);
	}
	
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void addUserGroupWhenSpecifiedRoleDoesNotExist() {
		UserGroup userGroup=new UserGroup();
		userGroup.setUserGroupName("userGroup1");
		Role role=new Role();
		role.setRoleName("role1");
		List<Role> roles=new ArrayList<>();
		roles.add(role);
		userGroup.setRoles(roles);
		Mockito.when(userGroupRepository.findByuserGroupName("userGroup1")).thenReturn(userGroup);
		Mockito.when(roleService.findRole(role.getRoleName())).thenReturn(null);
		userGroupService.addUserGroup(userGroup);
     
	}
	@Test
	public void addUserGroupWhenCreatedSuccessfully() {
		UserGroup userGroup=new UserGroup();
		userGroup.setUserGroupName("userGroup1");
		Role role=new Role();
		role.setRoleName("role1");
		List<Role> roles=new ArrayList<>();
		roles.add(role);
		userGroup.setRoles(roles);
		Mockito.when(userGroupRepository.findByuserGroupName("userGroup1")).thenReturn(null);
		Mockito.when(roleService.findRole(role.getRoleName())).thenReturn(role);
		Mockito.when(userGroupRepository.save(userGroup)).thenReturn(userGroup);
		UserGroup createdUserGroup = userGroupService.addUserGroup(userGroup);
		Assert.assertNotNull(createdUserGroup,"addUserGroup is not created Successfully..");
     
	}
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void updateUserGroupWhenUserGroupDoesNotExists() {
		
		UserGroup userGroup=new UserGroup();
		userGroup.setUserGroupName("userGroup1");
		Mockito.when(userGroupRepository.findByuserGroupName("userGroup1")).thenReturn(null);
		userGroupService.updateUserGroup(userGroup);
	}
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void updateUserGroupWhenSpecifiedRoleDoesNotExist() {
		UserGroup userGroup=new UserGroup();
		userGroup.setUserGroupName("userGroup1");
		Role role=new Role();
		role.setRoleName("role1");
		List<Role> roles=new ArrayList<>();
		roles.add(role);
		userGroup.setRoles(roles);
		Mockito.when(userGroupRepository.findByuserGroupName("userGroup1")).thenReturn(userGroup);
		Mockito.when(roleService.findRole(role.getRoleName())).thenReturn(null);
		userGroupService.updateUserGroup(userGroup);
     
	}
	
	@Test
	public void updateUserWhenCreatedSuccessfully() {
		UserGroup userGroup=new UserGroup();
		userGroup.setUserGroupName("userGroup1");
		Role role=new Role();
		role.setRoleName("role1");
		List<Role> roles=new ArrayList<>();
		roles.add(role);
		userGroup.setRoles(roles);
		Mockito.when(userGroupRepository.findByuserGroupName("userGroup1")).thenReturn(userGroup);
		Mockito.when(roleService.findRole(role.getRoleName())).thenReturn(role);
		Mockito.when(userGroupRepository.save(userGroup)).thenReturn(userGroup);
		UserGroup updatedUserGroup = userGroupService.updateUserGroup(userGroup);
		Assert.assertNotNull(updatedUserGroup,"update2UserGroup is not created Successfully..");
     
	}

}
