package com.uxpsystems.assignment.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class User extends Auditable {

	private static final long serialVersionUID = 1L;

	public User() {
	}

	public User(User user) {
		this.userName = user.userName;
		this.saltedPassword = user.saltedPassword;
		this.isActive = true;
		this.roles = user.roles;
	}

	@Column(unique=true)
	@NotBlank
	protected String userName;
	@NotBlank
	protected String saltedPassword;
	
	protected boolean isActive=true;
	
	@ManyToMany(fetch=FetchType.EAGER)
	protected Set<Role> roles = new HashSet<Role>();
	
	@Email
	private String email;

}
