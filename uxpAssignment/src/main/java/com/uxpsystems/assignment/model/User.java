package com.uxpsystems.assignment.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
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
		this.isActive = user.isActive;
		this.authorities = user.authorities;
	}

	@Column(unique=true)
	@NotBlank
	protected String userName;
	@NotBlank
	protected String saltedPassword;
	@NotBlank
	protected boolean isActive;
	
	@ManyToMany(fetch=FetchType.EAGER)
	protected Set<Role> authorities = new HashSet<Role>();
	

}
