package com.uxpsystems.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.exeception.UXPExecption;
import com.uxpsystems.assignment.model.CreateUSer;
import com.uxpsystems.assignment.model.Role;
import com.uxpsystems.assignment.model.User;

@Service
public interface UserOperationService {

	public Optional<User> getUser(Authentication auth);

	@Secured({ "ROLE_ADMIN", "ROLE_SUPERUSER" })
	void addUser(String userNAme, String password, String email, String roleName);

	@Secured({ "ROLE_USER", "ROLE_READ", "ROLE_WRITE", "ROLE_ADMIN", "ROLE_SUPERUSER" })
	public List<User> getAllUser();

	@Secured({ "ROLE_USER", "ROLE_READ", "ROLE_WRITE", "ROLE_ADMIN", "ROLE_SUPERUSER" })
	public User getUserByID(Long id);

	@Secured({ "ROLE_ADMIN", "ROLE_SUPERUSER" })
	public void deleteUserByID(Long id, Authentication auth) throws UXPExecption;

	@Secured({ "ROLE_ADMIN", "ROLE_SUPERUSER", "ROLE_WRITE" })
	public void updateUser(CreateUSer user);

}
