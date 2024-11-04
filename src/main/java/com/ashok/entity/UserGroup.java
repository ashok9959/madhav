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
public class UserGroup {
	@Id	
	//usergroup name should not extends the 16 characters
	@Size(max = 6, message = "User group name must not exceed 16 characters")
	private String userGroupName;
	//desc is a optional it can be empty and if it not empty if less than the 200 char
	 @Size(max = 200, message = "Description must not exceed 200 characters")
	private String description;
	@ManyToMany(cascade = {CascadeType.MERGE} ,fetch = FetchType.LAZY )
	//role should not be empty
	 @NotEmpty(message = "Roles cannot be empty")
	List<Role>roles;
	@ManyToMany(mappedBy = "userGroup" )
	@JsonIgnore
	@Column(nullable = true)
	List<User> user;
}
