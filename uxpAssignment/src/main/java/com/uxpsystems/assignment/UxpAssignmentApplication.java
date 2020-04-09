package com.uxpsystems.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UxpAssignmentApplication {

	
	public static void main(String[] args) {
		
		SpringApplication.run(UxpAssignmentApplication.class, args);
	}

}
