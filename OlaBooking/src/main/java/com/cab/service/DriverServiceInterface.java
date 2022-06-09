package com.cab.service;

import com.cab.exceptions.CustomerException;
import com.cab.exceptions.DriverException;
import com.cab.model.Cab;
import com.cab.model.Driver;
import com.cab.model.TripBooking;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface DriverServiceInterface {
	
	
	
    Driver addDriver(Driver driver);

    String getDlByID(Integer id) throws DriverException;

    Driver updateDriver(Integer id, Driver driver) throws DriverException;

    Driver deleteDriver(Integer id) throws DriverException;

    Driver getDriverByID(Integer id) throws DriverException;

    List<Driver> getAllDrivers();


}
