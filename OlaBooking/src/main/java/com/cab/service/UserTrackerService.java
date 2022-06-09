package com.cab.service;

import java.util.Objects;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.exceptions.UserException;
import com.cab.model.User;
import com.cab.model.UserTracker;
import com.cab.repository.UserDAO;
import com.cab.repository.UserTrackerDAO;

@Service
public class UserTrackerService {

    @Autowired
    UserTrackerDAO userTrackerDAO;

    @Autowired
    UserDAO userDAO;

    
    
    public boolean isLoggedIn(User user) {
    	
        Optional<UserTracker> userid = userTrackerDAO.findByUserId(user.getUserId());
        
        return userid.isPresent();
        
    }

    
    
    public boolean loginUser(User user) throws UserException {
    	
        try {
        	
			User userDB = userDAO.findByUsername(user.getUsername()).get();
			
            if (userDB == null)
                throw new UserException("User not found");
            
            if (!Objects.equals(userDB.getPassword(), user.getPassword()))
                return false;

            UserTracker ut = new UserTracker();
            ut.setUserId(userDB.getUserId());
            userTrackerDAO.save(ut);
            
        } catch (ConstraintViolationException e) {
        	
            return false;
        }

        return true;
    }

    
    
    public boolean logoutUser(User user) {
    	
        Optional<UserTracker> ut = userTrackerDAO.findByUserId(user.getUserId());
        
        if (ut.isEmpty())
            return false;
        
        userTrackerDAO.delete(ut.get());
        
        return true;
    }
}
