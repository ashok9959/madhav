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
import com.ashok.entity.Workflow;
import com.ashok.exception.BusinessValidationException;
import com.ashok.repository.RoleRepository;
import com.ashok.repository.WorkflowRepository;


public class RoleServiceTest {
	@InjectMocks
	RoleService roleService=new RoleService();
	
	@Mock
	RoleRepository roleRepository;
	@Mock
	WorkflowRepository workflowRepository;
	@Mock
	WorkflowService workflowService;
	
	@BeforeClass
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void addRoleWhenRoleAlreadyExists() {
		
		Role role=new Role();
		role.setRoleName("role1");
		Mockito.when(roleRepository.findByroleName("role1")).thenReturn(role);
		roleService.addRole(role);
		
	}
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void addRoleWhenSpecifiedWorkflowDoesNotExist() {
		Role role=new Role();
		role.setRoleName("role1");
		Workflow workflow=new Workflow();
		workflow.setWorkFlowName("workflow1");
		List<Workflow> wf=new ArrayList<>();
		wf.add(workflow);
		role.setWorkflows(wf);
		Mockito.when(roleRepository.findByroleName("role1")).thenReturn(role);
		Mockito.when(workflowService.findWorkflow(workflow.getWorkFlowName())).thenReturn(null);
		roleService.addRole(role);
	}
	
	@Test
	public void addRoleWhenCreatedSuccessfully() {
		Role role=new Role();
		role.setRoleName("role1");
		Workflow workflow=new Workflow();
		workflow.setWorkFlowName("workflow1");
		List<Workflow> wf=new ArrayList<>();
		wf.add(workflow);
		role.setWorkflows(wf);
		Mockito.when(roleRepository.findByroleName("role1")).thenReturn(null);
		Mockito.when(workflowService.findWorkflow(workflow.getWorkFlowName())).thenReturn(workflow);
		Mockito.when(roleRepository.save(role)).thenReturn(role);
		Role createdRole = roleService.addRole(role);
		Assert.assertNotNull(createdRole,"addrole is not created Successfully..");
	}
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void updateRoleWhenRoleDoesNotExists() {
		
		Role role=new Role();
		role.setRoleName("role1");
		Mockito.when(roleRepository.findByroleName("role1")).thenReturn(null);
		roleService.updateRole(role);
		
	}
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void updateRoleWhenSpecifiedWorkflowDoesNotExist() {
		Role role=new Role();
		role.setRoleName("role1");
		Workflow workflow=new Workflow();
		workflow.setWorkFlowName("workflow1");
		List<Workflow> wf=new ArrayList<>();
		wf.add(workflow);
		role.setWorkflows(wf);
		Mockito.when(roleRepository.findByroleName("role1")).thenReturn(role);
		Mockito.when(workflowService.findWorkflow(workflow.getWorkFlowName())).thenReturn(null);
		roleService.updateRole(role);
	}
	
	@Test
	public void updateRoleWhenCreatedSuccessfully() {
		Role role=new Role();
		role.setRoleName("role1");
		Workflow workflow=new Workflow();
		workflow.setWorkFlowName("workflow1");
		List<Workflow> wf=new ArrayList<>();
		wf.add(workflow);
		role.setWorkflows(wf);
		Mockito.when(roleRepository.findByroleName("role1")).thenReturn(role);
		Mockito.when(workflowService.findWorkflow(workflow.getWorkFlowName())).thenReturn(workflow);
		Mockito.when(roleRepository.save(role)).thenReturn(role);
		Role updatedRole = roleService.updateRole(role);
		Assert.assertNotNull(updatedRole,"addrole is not created Successfully..");
	}

}
