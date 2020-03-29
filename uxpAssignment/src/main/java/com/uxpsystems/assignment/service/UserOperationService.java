package com.uxpsystems.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.model.CreateUSer;
import com.uxpsystems.assignment.model.User;

@Service
public interface UserOperationService {

	public Optional<User> getUser(Authentication auth);

	void addUser(String userNAme, String password, String email,  String roleName);

	public List<User> getAllUser();

	public User getUserByID(Long id);
	
	public void deleteUserByID(Long id);

	public void updateUser(CreateUSer user);

}
