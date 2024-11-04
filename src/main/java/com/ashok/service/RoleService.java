package com.ashok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashok.entity.Role;
import com.ashok.entity.Workflow;
import com.ashok.exception.BusinessValidationException;
import com.ashok.repository.RoleRepository;
import com.ashok.repository.WorkflowRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	WorkflowRepository workflowRepository;
	
	@Autowired
	WorkflowService workflowService;

	public Role addRole(Role role) {
		// TODo check if specified role database,if it is there throw the exception
		Role existingRole = this.findRole(role.getRoleName());
		if (existingRole != null) {
			throw new BusinessValidationException("role is already exist...");
		}
		
		
		
		List<Workflow> workflows = role.getWorkflows();
		for(Workflow wf:workflows) {
			Workflow workflow = workflowService.findWorkflow(wf.getWorkFlowName());
			if(workflow==null) {
				 throw new BusinessValidationException("specified workflow  is not availabel ! "+wf.getWorkFlowName());
			}
		}
           
		return roleRepository.save(role);
	}
	
	public Role updateRole(Role role) {
		//TODo check if specified user database,if it is not there throw the exception
		Role role2 = this.findRole(role.getRoleName());
		if (role2 == null) {
			throw new BusinessValidationException("role does not exist...");
		}
		List<Workflow> workflows = role.getWorkflows();
		for(Workflow wf:workflows) {
			Workflow workflow = workflowService.findWorkflow(wf.getWorkFlowName());
			if(workflow==null) {
				 throw new BusinessValidationException("specified workflow  is not availabel ! "+wf.getWorkFlowName());
			}
		}
		return roleRepository.save(role);
	}
	

	

	public List<Role> getAllRoles() {

		return roleRepository.findAll();
	}

	

//	public List<Workflow> getWorkFlowforRoleName(String name) {
//
//		return workflowRepository.findByRoles_RoleName(name);
//	}

	public Role findRole(String name) {

		return roleRepository.findByroleName(name);
	}

	

}
