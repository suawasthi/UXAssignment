package com.uxpsystems.assignment;

import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uxpsystems.assignment.model.Admin;
import com.uxpsystems.assignment.service.UserOperationService;

import junit.framework.Assert;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserOperationServiceTest {
	
	
	@InjectMocks
	public UserOperationService userService;
	
	@SuppressWarnings("deprecation")
	@Test
	public void whenUserByIDthenRetriveUser() {
		
	
		
			
	      
	      Assert.assertEquals(userService.getUserByID(1L).getUserName(),"Admin");
	}
	
}
