package com.uxpsystems.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uxpsystems.assignment.service.UserOperationService;

@Controller
public class LoginController {
	@Autowired
	UserOperationService userService;
	
	@RequestMapping("/trial")
	public String getIndex(Authentication auth) {
		return "index.html";
		
	}
	
	

	
}
