package com.uxpsystems.assignment.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeat {
	@RequestMapping("/Alive")
	public String heatBeat() {
		LocalDate date = LocalDate.now();
		return "Alive at : " + date;
	}
}
