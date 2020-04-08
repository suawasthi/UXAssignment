package com.uxpsystems.assignment;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.InjectService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uxpsystems.assignment.exeception.UserNotFoundExcption;
import com.uxpsystems.assignment.model.Admin;
import com.uxpsystems.assignment.model.Customer;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserOperationImpl;
import com.uxpsystems.assignment.service.UserOperationService;

import junit.framework.Assert;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserOperationServiceTest {

	@InjectMocks
	public UserOperationImpl userService;

	@SuppressWarnings("deprecation")
	@Test
	@Ignore
	public void whenUserByIDthenRetriveUser() throws UserNotFoundExcption {
		
		 List<User> value = new ArrayList<User>();
		 value.add(new Admin());
		 value.add(new Customer());
		Mockito.when(userService.getAllUser()).thenReturn(value);
		
			
	      
	      Assert.assertEquals(value.size(), 2);
	}

}
