package com.uxpsystems.assignment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.exeception.UXPExecption;
import com.uxpsystems.assignment.exeception.UserNotFoundExcption;
import com.uxpsystems.assignment.model.CreateUSer;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserOperationService;

import net.minidev.json.JSONObject;

@RestController
public class UserOperationController {

	
    private static final Logger LOGGER=LoggerFactory.getLogger(UserOperationController.class);

	
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
		LOGGER.info("UserOperationController:: User Details :: By- " + auth.getName());
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
		LOGGER.debug("UserOperationController:: User Details :: successfull- " + jsonObject);

		return jsonObject;
	}

	@PutMapping(value = "/update-user")
	public JSONObject updateUser(Authentication auth, @RequestBody CreateUSer user) {
		LOGGER.info("UserOperationController:: Update  User :: By- " + auth.getName());
		LOGGER.debug("User : " + user);
		userService.updateUser(user);
		LOGGER.info("UserOperationController:: Update User :: user updated sucessfully");

		return success;
	}

	@GetMapping("/all-user")
	public List<User> getAllUser() {
		LOGGER.info("UserOperationController:: All User ");
		
		return userService.getAllUser();
	}

	@GetMapping("/getUserByID")
	public User getUser(Authentication auth, @RequestParam(name = "id") Long id) throws UserNotFoundExcption {
		LOGGER.info("UserOperationController:: getUser By ID " + id);

		return userService.getUserByID(id);
	}

	@DeleteMapping("/delete-user")
	public JSONObject deleteUserByID(Authentication auth, @RequestParam(name = "id") Long id) throws UXPExecption {
		LOGGER.info("UserOperationController:: deleteUserByID By ID " + id);

		userService.deleteUserByID(id, auth);

		return success;
	}

	@ExceptionHandler(UXPExecption.class)
	public final ResponseEntity<String> handleCustomException(UXPExecption exception) {
		LOGGER.info("UserOperationController::handleCustomException " );

		 List<String> details = new ArrayList<>();
	       details.add(exception.getLocalizedMessage());
	       System.out.println("trial");
	       
        ErrorResponse error = new ErrorResponse(details, exception.getMessage());

		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	@PostMapping(value = "/create-user")
	@ResponseStatus()
	public JSONObject createUser(Authentication auth, @Valid @RequestBody CreateUSer user) {
		LOGGER.info("UserOperationController::createUser   "+ user  );

		userService.addUser(user.getUserName(), user.getPassword(), user.getEmail(), user.getRole());
		LOGGER.info("UserOperationController::createUser :: user added sucessfully with username "+user.getUserName());
		return success;
	}
	
	@RequestMapping("/noAccess")
	public String noAcess() {
		
		LOGGER.info("UserOperationController::noAcess  :: No authrorization" );
		return "Permission denied";
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class, UserNotFoundExcption.class, IllegalStateException.class})
	public Map<String, String> handleValidationExceptions(

			MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    LOGGER.info(errors.toString());
	    return errors;
	}
}
