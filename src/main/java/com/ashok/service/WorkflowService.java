package com.ashok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashok.entity.Workflow;
import com.ashok.exception.BusinessValidationException;
import com.ashok.repository.WorkflowRepository;

@Service
public class WorkflowService {

	@Autowired
	WorkflowRepository workflowRepository;

	public Workflow addWorkflow(Workflow workflow) {
		Workflow existingWorkflow = this.findWorkflow(workflow.getWorkFlowName());
		if (existingWorkflow != null) {
			throw new BusinessValidationException("workflow is already exists..");
		}

		return workflowRepository.save(workflow);
	}

	public Workflow updateWorkflow(Workflow workflow) {
		Workflow existingWorkflow = this.findWorkflow(workflow.getWorkFlowName());
		if (existingWorkflow == null) {
			throw new BusinessValidationException("workflow doesn't exists..");
		}

		return workflowRepository.save(workflow);
	}

	public List<Workflow> getWorkFlow() {

		return workflowRepository.findAll();
	}

	public Workflow findWorkflow(String name) {

		return workflowRepository.findByworkFlowName(name);
	}

}
