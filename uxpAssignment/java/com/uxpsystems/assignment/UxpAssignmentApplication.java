package com.uxpsystems.assignment;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UxpAssignmentApplication {
    private static final Logger LOGGER=LoggerFactory.getLogger(UxpAssignmentApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Application stating on server at : " + new Date());
		SpringApplication.run(UxpAssignmentApplication.class, args);
		LOGGER.info("Server operation completed on server at : " + new Date());

	}

}
