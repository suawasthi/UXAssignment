package com.uxpsystems.assignment.service;

import java.io.EOFException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.dao.AdminRepo;
import com.uxpsystems.assignment.dao.ConsumerRepo;
import com.uxpsystems.assignment.dao.RoleRepo;
import com.uxpsystems.assignment.dao.UserRepo;
import com.uxpsystems.assignment.model.Admin;
import com.uxpsystems.assignment.model.CreateUSer;
import com.uxpsystems.assignment.model.Customer;
import com.uxpsystems.assignment.model.Role;
import com.uxpsystems.assignment.model.User;

@Service
public class UserOperationImpl implements UserOperationService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	ConsumerRepo consumerRepo;

	@Autowired
	RoleRepo roleRepo;

	public Optional<User> getUser(Authentication auth) {
		return userRepo.findByUserName(auth.getName());
	}

	@Override
	public void addUser(String userNAme, String password, String email, String roleName) {
		User user = roleName.equals("IS_ADMIN") ? new Admin() : new Customer();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		user.setSaltedPassword(passwordEncoder.encode(password));
		user.setUserName(userNAme);
		user.setEmail(email);

		List<Role> roles = roleRepo.findAll();
		Set<Role> roless = new HashSet<Role>();
		for (Role role : roles) {
			if (role.equals(roleName)) {
				roless.add(role);
			}
		}

		if (roleName.equals("IS_ADMIN")) {
			adminRepo.save((Admin) user);
		} else {
			consumerRepo.save((Customer) user);
		}
	}

	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public User getUserByID(Long id) {
		Optional<User> user = userRepo.findById(id);
		return user.get();
	}

	@Override
	public void deleteUserByID(Long id) {
		userRepo.deleteById(id);

	}

	@Override
	public void updateUser(CreateUSer user) {
		Optional<User> optionalUser = userRepo.findById(user.getID());
		if (!optionalUser.isPresent()) {

		} else {
			optionalUser.get().setEmail(user.getEmail());
			optionalUser.get().setActive(user.getIsActive());
			optionalUser.get().setUserName(user.getUserName());
			userRepo.save(optionalUser.get());

		}
	}

}
