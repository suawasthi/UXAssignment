package com.uxpsystems.assignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.exeception.UXPExecption;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserOperationService;

import net.minidev.json.JSONObject;

@RestController
public class UserOperationController {

	@Autowired
	UserOperationService userService;

	@RequestMapping("/userDetails")
	public JSONObject getUserDetails(Authentication auth) throws UXPExecption {
		Optional<User> users = userService.getUser(auth);
		if (!users.isPresent()) {
			throw new UXPExecption("No User found");
		}
		JSONObject jsonObject = new JSONObject();
		if (users.get().isActive()) {
			jsonObject.put("username", users.get().getUserName());
			return jsonObject;
		}
		jsonObject.put("username", "");
		return jsonObject;
	}

	@RequestMapping("/create-user")
	public void createUser(Authentication auth, @RequestParam(name = "username1") String name,
			@RequestParam(name = "password") String password, @RequestParam(name = "email") String emmail,
			@RequestParam(name = "role") String role) {

		userService.addUser(name, password, emmail, role);

	}
	
	@GetMapping("/all-user")
	public List<User>  getAllUser() {
		System.out.println("***************let go ");
		return userService.getAllUser();
	}
	
	@GetMapping("/getUserByID")
	public User getUser(Authentication auth , @RequestParam(name="id") Long id) {
		return userService.getUserByID(id);
	}

	@GetMapping("/delete-user")
	public void deleteUserByID(Authentication auth , @RequestParam(name="id") Long id) {
		 userService.deleteUserByID(id);
	}
}
