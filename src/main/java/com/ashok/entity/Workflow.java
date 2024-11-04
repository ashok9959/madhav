package com.ashok.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Workflow {
	@Id
	// workflow name should not extends the 16 characters
		@Size(max = 6, message = "workflow name must not exceed 16 characters")
	String workFlowName;
	
	// desc is a optional it can be empty and if it not empty if less than the 200
	@Size(max = 200, message = "Description must not exceed 200 characters")
	String workFlowDesc;
	@ManyToMany(mappedBy = "workflows")
	@JsonIgnore
	List<Role> roles;

}
