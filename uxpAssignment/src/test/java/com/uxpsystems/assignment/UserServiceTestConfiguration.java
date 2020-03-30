package com.uxpsystems.assignment;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.uxpsystems.assignment.service.UserOperationService;

@Profile("test")
@Configuration
public class UserServiceTestConfiguration {

	@Bean
	@Primary
	public UserOperationService userService() {
		return Mockito.mock(UserOperationService.class);
	}

}
