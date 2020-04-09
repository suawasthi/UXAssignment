package com.uxpsystems.assignment.bootStrap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.uxpsystems.assignment.dao.AdminRepo;
import com.uxpsystems.assignment.dao.ConsumerRepo;
import com.uxpsystems.assignment.dao.RoleRepo;
import com.uxpsystems.assignment.model.Admin;
import com.uxpsystems.assignment.model.Role;
import com.uxpsystems.assignment.model.User;

@Component
public class DEVBootStrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	RoleRepo roleRepo;

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	ConsumerRepo consumerRepo;
	
	@Autowired
	private HazelcastInstance hazelCastInstance;

	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
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
		
		
		java.util.Map<Long,User> stringStringMap = hazelCastInstance.getMap("configuration");
		List<User> cacheUser = hazelCastInstance.getList("userList");
		for(Admin admi : adminRepo.findAll()) {
			System.out.println(admi);
			cacheUser.add(admi);
			stringStringMap.put(admi.getID(), admi);
		}
		System.out.println(stringStringMap.values());
		System.out.println(stringStringMap.keySet());
		System.out.println("user added " + cacheUser);

		
		
		
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
