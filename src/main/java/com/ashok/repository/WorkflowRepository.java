package com.ashok.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashok.entity.Workflow;
@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, String>{

	//List<Workflow> findByRoles_RoleName(String name);


	//Workflow findByworkFlowName(String name);
	Workflow findByworkFlowName(String name);

	
	

}
