package com.uxpsystems.assignment.service;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.uxpsystems.assignment.exeception.UXPExecption;
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
		User user = new Customer();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		user.setSaltedPassword(passwordEncoder.encode(password));
		user.setUserName(userNAme);
		user.setEmail(email);

		Optional<Role> roles = roleRepo.findByName(roleName);
		if(!roles.isPresent()) {
			//create new role TODO
		}else {
			user.setRoles(roles.get());
		}

		consumerRepo.save((Customer)user);
	}

	@Override
	public List<User> getAllUser() {
		List<Admin> usr= adminRepo.findAll();
		List<Customer> consumer = consumerRepo.findAll();
		
		List<User> user = new ArrayList<User>();
		for(Admin admin: usr) {
			user.add(admin);
		}
		for(Customer cust: consumer) {
			user.add(cust);
		}
		return user;
		
	}

	@Override
	public User getUserByID(Long id) {
		Optional<User> user = userRepo.findById(id);
		return user.get();
	}

	@Override
	public void deleteUserByID(Long id, Authentication authentication) throws UXPExecption {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UXPExecption("User does not exists");
		}
		if (user.get().getUserName().equals(authentication.getName())) {
			throw new UXPExecption("Can't delete current user");
		}
		userRepo.deleteById(id);

	}

	@Override
	public void updateUser(CreateUSer user) {
		Optional<User> optionalUser = userRepo.findById(user.getID());
		if (!optionalUser.isPresent()) {

		} else {
			Optional<Role> role = roleRepo.findByName(user.getRole());
			optionalUser.get().setEmail(user.getEmail());
			optionalUser.get().setActive(user.getIsActive());
			optionalUser.get().setUserName(user.getUserName());
			optionalUser.get().setRoles(role.get());
			userRepo.save(optionalUser.get());

		}
	}

}
