package com.uxpsystems.assignment.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

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
	@NotBlank(message="Username can't be blank or dublicate")
	protected String userName;
	@NotBlank(message="Password can't be empty")
	protected String saltedPassword;
	
	protected boolean isActive=true;
	
	@JsonIdentityReference
	@ManyToOne
	Role roles;
	
	@Email(message="Not a valid email address")
	private String email;

}
