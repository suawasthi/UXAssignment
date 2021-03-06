package com.uxpsystems.assignment.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import lombok.Data;

@Entity
@Data
public class Role extends Auditable {

	
	private static final long serialVersionUID = 1L;
	
	public Role(){}
	
	public Role(@NotBlank String roleName) {
		this.name=roleName;
	}
	
	@NotBlank
	String name;
	
	

}
