package com.uxpsystems.assignment.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.exeception.UXPExecption;
import com.uxpsystems.assignment.model.CreateUSer;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserOperationService;

import net.minidev.json.JSONObject;

@RestController
public class UserOperationController {

	@Autowired
	UserOperationService userService;

	@Autowired
	LoginController loginContoller;

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

	@PostMapping("/testing")
	public String someThing(@RequestParam("userName") String username, @RequestParam("password") String password,
			@RequestParam("email") String email) {
		return username + "asdf" + password + " email" + email;
	}

	
	@PutMapping(value="/update-user" ) 
	public void updateUser(Authentication auth, @RequestBody CreateUSer user) {
		System.out.println("inside update  method" + user.toString());
		userService.updateUser(user);
	}
	
	@GetMapping("/all-user")
	public List<User> getAllUser() {
		System.out.println("***************let go ");
		return userService.getAllUser();
	}

	@GetMapping("/getUserByID")
	public User getUser(Authentication auth, @RequestParam(name = "id") Long id) {
		return userService.getUserByID(id);
	}

	@DeleteMapping("/delete-user")
	public void deleteUserByID(Authentication auth, @RequestParam(name = "id") Long id) {
		userService.deleteUserByID(id);
	}

	@PostMapping(value="/create-user1" ) 
	public void createUser(Authentication auth, @RequestBody CreateUSer user) {
		System.out.println("inside this method" + user.toString());
		userService.addUser(user.getUserName(), user.getPassword(), user.getEmail(), "ADMIN");
		//loginContoller.getIndex(auth);
	}
}
