package com.uxpsystems.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("assignment")
public class LoginController {
	
	@RequestMapping("")
	public String getIndex() {
		return "index.html";
		
	}
	
}
