package com.ashok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashok.entity.Workflow;
import com.ashok.service.WorkflowService;

@RestController
public class WorkflowController {
	@Autowired
	WorkflowService workflowService;
	
	@PostMapping("/workflow")
	public Workflow addWorkflow(@RequestBody Workflow workflow) {
		return workflowService.addWorkflow(workflow);
	}
	
	@PutMapping("/workflow")
	public Workflow updateWorkflow(@RequestBody Workflow workflow) {
		return workflowService.updateWorkflow(workflow);
	}
	
	
	@GetMapping("/workflow") 
	public List<Workflow> getWorkFlowf(){
		return workflowService.getWorkFlow();
	}
	@GetMapping("/workflow/{name}")
	public Workflow findWorkflow(@PathVariable String name) {
		return workflowService.findWorkflow(name);
		
	}

}
