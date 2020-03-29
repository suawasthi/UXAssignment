package com.uxpsystems.assignment.model;

import lombok.Data;

@Data
public class CreateUSer {
	Long ID;
	Boolean isActive;
	String userName;
	String email;
	String password;
	String role;

}
