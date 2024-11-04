package com.ashok.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Role {
	@Id
	// role name should not extends the 16 characters
	@Size(max = 16, message = "Role name must not exceed 16 characters")
	String roleName;
	// desc is a optional it can be empty and if it not empty if less than the 200
	@Size(max = 200, message = "Description must not exceed 200 characters")
	String roleDesc;
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	// workflow it should not be empty
	@NotEmpty(message = "Workflow cannot be empty")
	List<Workflow> workflows;
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	@Column(nullable = true)
	List<UserGroup> userGroup;

}
