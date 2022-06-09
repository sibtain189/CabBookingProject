package com.cab.repository;

import org.apache.naming.java.javaURLContextFactory;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);
	
}
