package com.uxpsystems.assignment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {

	Optional<Role> findByName(String string);

}
