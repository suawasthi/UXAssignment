package com.uxpsystems.assignment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.model.User;

@Repository
public interface UserRepo  extends JpaRepository<User, Long>{

	Optional<User> findByUserName(String username);

}
