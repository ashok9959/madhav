package com.ashok.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ashok.entity.Workflow;
import com.ashok.exception.BusinessValidationException;
import com.ashok.repository.WorkflowRepository;

public class WorkflowServiceTest {
	@InjectMocks
	WorkflowService workflowService=new WorkflowService();
	@Mock
	WorkflowRepository workflowRepository;
	
	@BeforeClass
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void addWorkflowWhenWorkflowAlreadyExists() {
		Workflow workflow=new Workflow();
		workflow.setWorkFlowName("workflow1");
		Mockito.when(workflowRepository.findByworkFlowName(workflow.getWorkFlowName())).thenReturn(workflow);
		workflowService.addWorkflow(workflow);
		}
	
	@Test
	public void addWorkflowWhenWorkflowCreatedSuccessfully() {
		Workflow workflow=new Workflow();
		workflow.setWorkFlowName("workflow1");
		Mockito.when(workflowRepository.findByworkFlowName(workflow.getWorkFlowName())).thenReturn(null);
		Mockito.when(workflowRepository.save(workflow)).thenReturn(workflow);
		Workflow createdWorkflow = workflowService.addWorkflow(workflow);
		Assert.assertNotNull(createdWorkflow,"addWorkFlow is not created Successfully..");
		}
	
	@Test(expectedExceptions = BusinessValidationException.class)
	public void updateWorkflowWhenWorkflowDoesNotExists() {
		Workflow workflow=new Workflow();
		workflow.setWorkFlowName("workflow1");
		Mockito.when(workflowRepository.findByworkFlowName(workflow.getWorkFlowName())).thenReturn(null);
		workflowService.updateWorkflow(workflow);
		}
	@Test
	public void updateWorkflowWhenWorkflowCreatedSuccessfully() {
		Workflow workflow=new Workflow();
		workflow.setWorkFlowName("workflow1");
		Mockito.when(workflowRepository.findByworkFlowName(workflow.getWorkFlowName())).thenReturn(workflow);
		Mockito.when(workflowRepository.save(workflow)).thenReturn(workflow);
		Workflow updatedWorkflow = workflowService.updateWorkflow(workflow);
		Assert.assertNotNull(updatedWorkflow,"updateWorkFlow is not created Successfully..");
		}



}
