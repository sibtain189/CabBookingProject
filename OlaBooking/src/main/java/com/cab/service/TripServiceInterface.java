package com.cab.service;

import com.cab.exceptions.TripException;
import com.cab.model.TripBooking;

import java.util.List;

public interface TripServiceInterface {
	
	
    TripBooking addTrip(TripBooking tripBooking, Integer customerId, Integer driverId) throws TripException;

    TripBooking updateTrip(Integer id, TripBooking tripBooking) throws TripException;

    TripBooking deleteTrip(Integer id) throws TripException;

    List<TripBooking> getAllTripsByCustomer(Integer customerId) throws TripException;

    Double calculateBill(Integer id) throws TripException;

    TripBooking getTripById(Integer id) throws TripException;
    
    
}
