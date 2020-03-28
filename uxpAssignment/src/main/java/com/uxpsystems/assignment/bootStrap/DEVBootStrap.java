package com.uxpsystems.assignment.bootStrap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Autowired
	RoleRepo roleRepo;

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	ConsumerRepo consumerRepo;

	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		addRoles();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Admin admin = new Admin();
		admin.setSaltedPassword(passwordEncoder.encode("admin"));
		admin.setUserName("admin");
		admin.setActive(true);
		
		List<Role> roles = roleRepo.findAll();
		Set<Role> roleSet = new HashSet<Role>();
		for(Role role : roles) {
			if(role.getRoleName().equals("IS_ADMIN")) {
				roleSet.add(role);
			}
		}
		
		adminRepo.save(admin);
		
	}

	private void addRoles() {
		Role admin = new Role("IS_ADMIN", true);
		Role superUser = new Role("IS_SUPERUSER", true);
		Role user = new Role("USER", true);
		roleRepo.save(admin);
		roleRepo.save(superUser);
		roleRepo.save(user);
	}

}
