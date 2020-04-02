package com.uxpsystems.assignment.service;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.controller.UserOperationController;
import com.uxpsystems.assignment.dao.AdminRepo;
import com.uxpsystems.assignment.dao.ConsumerRepo;
import com.uxpsystems.assignment.dao.RoleRepo;
import com.uxpsystems.assignment.dao.UserRepo;
import com.uxpsystems.assignment.exeception.UXPExecption;
import com.uxpsystems.assignment.exeception.UserNotFoundExcption;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(UserOperationImpl.class);

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
		if (!roles.isPresent()) {
			LOGGER.debug("No roles present");
		} else {
			user.setRoles(roles.get());
		}

		Customer cust = consumerRepo.save((Customer) user);
		LOGGER.debug("Customer added as : " + cust);
	}

	@Override
	public List<User> getAllUser() {
		List<Admin> usr = adminRepo.findAll();
		List<Customer> consumer = consumerRepo.findAll();

		List<User> user = new ArrayList<User>();
		for (Admin admin : usr) {
			user.add(admin);
		}
		for (Customer cust : consumer) {
			user.add(cust);
		}
		return user;

	}

	@Override
	public User getUserByID(Long id) throws UserNotFoundExcption {
		LOGGER.info("Inside service :: getUserByID :: By  ID" + id);
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundExcption("User not found with resect to ID ");
		}
		LOGGER.debug("Inside service :: getUserByID :: User found " + user.get());
		return user.get();
	}

	@Override
	public void deleteUserByID(Long id, Authentication authentication) throws UXPExecption {
		LOGGER.info("Inside service :: deleteUserByID :: By  ID" + id);

		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UXPExecption("User does not exists");
		}
		if (user.get().getUserName().equals(authentication.getName())) {
			throw new UXPExecption("Can't delete current user");
		}
		userRepo.deleteById(id);
		LOGGER.info("Inside service :: deleteUserByID :: By  ID" + id + " Successfull");

	}

	@Override
	public void updateUser(CreateUSer user) {
		LOGGER.info("Inside service :: update user with :: " + user);

		Optional<User> optionalUser = userRepo.findById(user.getID());
		if (!optionalUser.isPresent()) {

		} else {
			Optional<Role> role = roleRepo.findByName(user.getRole());
			optionalUser.get().setEmail(user.getEmail());
			optionalUser.get().setActive(user.getIsActive());
			optionalUser.get().setUserName(user.getUserName());
			optionalUser.get().setRoles(role.get());
			User savedUser = userRepo.save(optionalUser.get());
			LOGGER.debug("Inside service :: saved object " + savedUser.toString());

		}
	}

}
