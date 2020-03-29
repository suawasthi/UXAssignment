package com.uxpsystems.assignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

	private static JSONObject success;

	static {
		success = new JSONObject();
		success.put("status", "success");
	}

	

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

	@PutMapping(value = "/update-user")
	public JSONObject updateUser(Authentication auth, @RequestBody CreateUSer user) {
		userService.updateUser(user);
		return success;
	}

	@GetMapping("/all-user")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	@GetMapping("/getUserByID")
	public User getUser(Authentication auth, @RequestParam(name = "id") Long id) {
		return userService.getUserByID(id);
	}

	@DeleteMapping("/delete-user")
	public JSONObject deleteUserByID(Authentication auth, @RequestParam(name = "id") Long id) throws UXPExecption {

		userService.deleteUserByID(id, auth);

		return success;
	}

	@ExceptionHandler(UXPExecption.class)
	public final ResponseEntity<String> handleCustomException(UXPExecption exception) {
		
		 List<String> details = new ArrayList<>();
	       details.add(exception.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(details, exception.getMessage());

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@PostMapping(value = "/create-user")
	public JSONObject createUser(Authentication auth, @RequestBody CreateUSer user) {
		userService.addUser(user.getUserName(), user.getPassword(), user.getEmail(), user.getRole());
		return success;
	}
	
	@RequestMapping("/noAccess")
	public String noAcess() {
		return "Permission denied";
	}
}
