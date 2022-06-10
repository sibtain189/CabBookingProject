package com.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.model.Cab;

import java.util.List;

@Repository
public interface CabDAO extends JpaRepository<Cab, Integer> {
	
    List<Cab> findAllByCabType(String cabType);

}
