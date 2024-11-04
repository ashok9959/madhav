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

import com.ashok.entity.Role;
import com.ashok.entity.Workflow;
import com.ashok.exception.UserNotFoundException;
import com.ashok.service.RoleService;
import com.ashok.service.WorkflowService;



@RestController
public class RoleController {
	
	private Logger logger=LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	WorkflowService workflowService;
	@PostMapping("/role")
	public Role addRole(@RequestBody Role role) {
		return roleService.addRole(role);
		
	}
	
	
	@PutMapping("/role")
	public Role updateRole(@RequestBody Role role) {
		return roleService.updateRole(role);
	}
	@GetMapping("/role")
	public List<Role> getAllRoles(){
		return roleService.getAllRoles();
		
	}
	@GetMapping("/role/{name}")
	public Role findRole(@PathVariable String name){
		Role roles = roleService.findRole(name);
		if(roles==null) {
			throw new UserNotFoundException("role with name " + name + " not found");
		}
		return roles; 
		
	}
//	
//	@GetMapping("/workflow/{name}") 
//	public List<Workflow> getWorkFlowforrolename(@PathVariable String name){
//		List<Workflow> workFlowforRoleName = roleService.getWorkFlowforRoleName(name);
//		if(workFlowforRoleName==null || workFlowforRoleName.isEmpty()) {
//			throw new UserNotFoundException("workflowfor role  with name " + name + " not found");
//		}
//	return workFlowforRoleName;
//	}
	
	

}
