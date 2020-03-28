package com.uxpsystems.assignment.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Role extends Auditable {

	
	private static final long serialVersionUID = 1L;
	
	public Role(){}
	
	public Role(@NotBlank String roleName, boolean isroleActive) {
		this.roleActive=isroleActive;
		this.roleName=roleName;
	}
	
	
	@NotBlank
	String roleName;
	
	boolean roleActive;

}
