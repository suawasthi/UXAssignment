package com.uxpsystems.assignment.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateUSer {
	Long ID;
	Boolean isActive;
	@NotBlank(message="Can't be blank")
	String userName;
	@Email(message="Invalid email structure")
	String email;
	@NotBlank(message="Password can't be blank")
	String password;
	
	String role;

}
