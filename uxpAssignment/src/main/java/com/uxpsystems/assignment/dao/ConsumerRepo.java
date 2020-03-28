package com.uxpsystems.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.model.Customer;

@Repository
public interface ConsumerRepo extends JpaRepository<Customer, Long>  {

}
