package com.cab.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.exceptions.TripException;
import com.cab.model.Cab;
import com.cab.model.Driver;
import com.cab.model.TripBooking;
import com.cab.model.TripStatus;
import com.cab.repository.TripBookingDAO;

@Service
public class TripService implements TripServiceInterface {

	@Autowired
	private TripBookingDAO tripBookingDAO;

	@Autowired
	private CustomerServiceInterface customerServiceInterface;

	@Autowired
	private DriverServiceInterface driverServiceInterface;

	@Override
	public TripBooking addTrip(TripBooking tripBooking, Integer customerId, Integer driverId) {
		tripBooking.setCustomer(customerServiceInterface.viewCustomer(customerId));
		tripBooking.setDriver(driverServiceInterface.getDriverByID(driverId));
		Driver driver = driverServiceInterface.getDriverByID(driverId);
		Cab cab = driver.getCab();
		tripBooking.setBill(tripBooking.getDistanceInKm() * cab.getPerKmRate());
		tripBooking.setStatus(TripStatus.CONFIRMED);
		
		tripBooking.setFromDateTime(LocalDate.now());;
		
//		tripBooking.setToDateTime(LocalDateTime.now());
		
		return tripBookingDAO.save(tripBooking);
	}

	@Override
	public TripBooking updateTrip(Integer id, TripBooking tripBooking) throws TripException {
		TripBooking trip1 = tripBookingDAO.findById(id)
				.orElseThrow(() -> new TripException("Trip with id : " + id + " does not exit.."));
		trip1.setFromLocation(tripBooking.getFromLocation());
		trip1.setToLocation(tripBooking.getToLocation());
		trip1.setFromDateTime(tripBooking.getFromDateTime());
//		trip1.setToDateTime(tripBooking.getToDateTime());
		trip1.setBill(tripBooking.getBill());
		trip1.setStatus(tripBooking.getStatus());
		trip1.setDistanceInKm(tripBooking.getDistanceInKm());
		tripBookingDAO.save(trip1);
		return trip1;
	}

	@Override
	public TripBooking deleteTrip(Integer id) throws TripException {
		TripBooking trip1 = tripBookingDAO.findById(id)
				.orElseThrow(() -> new TripException("Trip with id : " + id + " does not exit.."));
		tripBookingDAO.deleteById(id);
		return trip1;
	}

	
	@Override
	public List<TripBooking> getAllTripsByCustomer(Integer customerId) throws TripException {
		List<TripBooking> list = customerServiceInterface.getList(customerId);
		if (list.size() == 0)
			throw new TripException("No trip history for this customer having id : " + customerId);
		return list;
	}

	@Override
	public Double calculateBill(Integer id) throws TripException {
		TripBooking tripBooking = tripBookingDAO.findById(id)
				.orElseThrow(() -> new TripException("Trip with id : " + id + " does not exit.."));
		Double bill = tripBooking.getBill();
		if (bill == 0)
			throw new TripException("No trip/bill found for customerId : " + id);
		return bill;
	}

	@Override
	public TripBooking getTripById(Integer id) throws TripException {

		return tripBookingDAO.findById(id)
				.orElseThrow(() -> new TripException("Trip with id : " + id + " does not exit.."));
	}
}
