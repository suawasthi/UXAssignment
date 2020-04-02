package com.uxpsystems.assignment.bootStrap;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.uxpsystems.assignment.dao.AdminRepo;
import com.uxpsystems.assignment.dao.ConsumerRepo;
import com.uxpsystems.assignment.dao.RoleRepo;
import com.uxpsystems.assignment.model.Admin;
import com.uxpsystems.assignment.model.Role;

@Component
public class DEVBootStrap implements ApplicationListener<ContextRefreshedEvent> {

	Logger LOGGER = LoggerFactory.getLogger(DEVBootStrap.class);
	
	@Autowired
	RoleRepo roleRepo;

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	ConsumerRepo consumerRepo;

	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOGGER.info("Bootstart started :: onApplicationEvent");
		addRoles();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Admin admin = new Admin();
		admin.setSaltedPassword(passwordEncoder.encode("admin"));
		admin.setUserName("Admin");
		admin.setEmail("admin@gmail.com");
		admin.setActive(true);
		
		Optional<Role> adminRole  = roleRepo.findByName("ADMIN");
		admin.setRoles(adminRole.get());
		
		
		
		
		
		Admin superUser = new Admin();
		superUser.setUserName("Ravish");
		superUser.setSaltedPassword(passwordEncoder.encode("ravish"));
		superUser.setEmail("ravish@gmail.com");
		superUser.setActive(true);
		
		Optional<Role> superUsers  = roleRepo.findByName("SUPERUSER");
		superUser.setRoles(superUsers.get());
		
		adminRepo.save(admin);
		adminRepo.save(superUser);
		
		LOGGER.debug("Bootstrap ended ");
		
	}

	private void addRoles() {
		
		Role admin = new Role("ADMIN");
		Role superUser = new Role("SUPERUSER");
		Role user = new Role("USER");
		Role read = new Role("READ");
		Role write = new Role("WRITE");
		
		roleRepo.save(superUser);
		roleRepo.save(user);
		roleRepo.save(admin);
		roleRepo.save(read);
		roleRepo.save(write);
	}

}
