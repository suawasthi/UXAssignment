package com.uxpsystems.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

}
