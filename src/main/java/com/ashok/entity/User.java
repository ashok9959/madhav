package com.ashok.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	
	// user name should not extends the 16 characters
	@Size(max = 6, message = "Username must not exceed 16 characters")
	String userName;
	
	// password should not be empty, cwp also should not be empty and pwd should be
	// the same
	// password size min should be 8 and it include small char and capital char ,
	// number and special char
	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
	String pwd;
	
	@NotEmpty(message = "Confirm password cannot be empty")
	String cpwd;
	
	// desc is a optional it can be empty and if it not empty if less than the 200
	@Size(max = 200, message = "Description must not exceed 200 characters")
	String description;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	// user group it should not be empty
	@NotEmpty(message = "User group cannot be empty")
	List<UserGroup> userGroup;
}
