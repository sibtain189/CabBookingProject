package com.cab.repository;

import com.cab.exceptions.TripException;
import com.cab.model.Customer;
import com.cab.model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface TripBookingDAO extends JpaRepository<TripBooking, Integer> {
	
	
}
