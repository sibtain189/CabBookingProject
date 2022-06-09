package com.cab.controller;

import com.cab.model.Driver;
import com.cab.model.UserRoles;
import com.cab.service.DriverServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabook/drivers")
public class DriverController {
	
    @Autowired
    private DriverServiceInterface driverServiceInterface;

    
    
    @PostMapping("/")
    public Driver addDriver(@RequestBody Driver driver) {
    	
        driver.setRoles(List.of(UserRoles.DRIVER));
        
        return driverServiceInterface.addDriver(driver);
    }
    
    
    @GetMapping("/")
    public List<Driver> getAllDrivers() {
    	
        return driverServiceInterface.getAllDrivers();
    }

    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable("id") Integer id) {
        return driverServiceInterface.getDriverByID(id);
    }

    @PutMapping("/{id}")
    public Driver updateDriver(@PathVariable("id") Integer id, @RequestBody Driver driver) {
        return driverServiceInterface.updateDriver(id, driver);
    }

    @DeleteMapping("/{id}")
    public Driver deleteDriver(@PathVariable("id") Integer id) {
        return driverServiceInterface.deleteDriver(id);
    }

   
}
