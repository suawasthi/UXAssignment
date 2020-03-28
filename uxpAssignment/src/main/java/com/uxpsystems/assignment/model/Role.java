package com.uxpsystems.assignment.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Role extends Auditable {

	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	String roleName;
	
	boolean roleActive;

}
