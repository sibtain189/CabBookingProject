package com.cab.service;

import com.cab.exceptions.CabException;
import com.cab.exceptions.CustomerException;
import com.cab.exceptions.DriverException;
import com.cab.model.Cab;
import com.cab.model.CabType;
import com.cab.model.Customer;
import com.cab.model.Driver;
import com.cab.repository.DriverDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService implements DriverServiceInterface {
	
	
    @Autowired
    private DriverDAO driverDAO;


    @Override
    public Driver addDriver(Driver driver) {
    	
        Cab cab = driver.getCab();
        
        CabType cabType = cab.getCabType();
        
        cab.setSittingCapcity(cabType.sittingCapacity());
        
        cab.setPerKmRate(cabType.getPrice());
        
        driver.setCab(cab);
                
        return driverDAO.save(driver);
    }

    
    @Override
    public String getDlByID(Integer id) throws DriverException {
        Driver driver = driverDAO.findById(id).orElseThrow(() -> new DriverException("Driver doesn't exist with id : " + id));
        return driver.getDl();
    }

    @Override
    public Driver updateDriver(Integer id, Driver driver) throws DriverException {
        Driver driver1 = driverDAO.findById(id).orElseThrow(() -> new DriverException("Driver doesn't exist with id : " + id));
        driver1.setCab(driver.getCab());
        driver1.setDl(driver.getDl());
        driver1.setRating(driver.getRating());
        driverDAO.save(driver1);
        return driver1;
    }

    @Override
    public Driver deleteDriver(Integer id) throws DriverException {
        Driver driver1 = driverDAO.findById(id).orElseThrow(() -> new DriverException("Driver doesn't exist with id : " + id));
        driverDAO.delete(driver1);
        return driver1;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverDAO.findAll();
    }

    @Override
    public Driver getDriverByID(Integer id) throws DriverException {
        return driverDAO.findById(id).orElseThrow(() -> new DriverException("Driver doesn't exist with id : " + id));
    }

	
	
}
