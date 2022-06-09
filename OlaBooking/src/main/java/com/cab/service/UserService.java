package com.cab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.exceptions.CabException;
import com.cab.exceptions.UserException;
import com.cab.model.User;
import com.cab.repository.UserDAO;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserTrackerService userTrackerService;

    
    
    @Override
    public User saveUser(User user) throws UserException {
    	
    	 User user2 = userDAO.save(user);
    	
    	 
        return user2;
    }
    

    @Override
    public User updateUser(Integer id, User user) throws UserException {
    	
        User curUser = userDAO.getById(id);
        
        if (curUser == null) throw new UserException("User doesn't exist with this userId : " + id);
        
        curUser.setUsername(user.getUsername());
        curUser.setName(user.getName());
        curUser.setEmail(user.getEmail());
        curUser.setPassword(user.getPassword());
        curUser.setMobileNo(user.getMobileNo());
        
        final User finalUser = curUser;
        
        return userDAO.save(finalUser);
    }

    
    
    @Override
    public User deleteUser(Integer id) throws UserException {
    	
        User delUser = userDAO.findById(id).orElseThrow(() -> new CabException("User doesn't exist with id : " + id));
        
        userDAO.delete(delUser);
        
        return delUser;
        
    }

    
    
    @Override
    public List<User> getAllUser() {
    	
        return userDAO.findAll();
    }
    
    
    @Override
    public User getUserById(Integer id) throws UserException {
    	
		return userDAO.findById(id).orElseThrow(() -> new UserException("User doesn't exist with id : " + id));
    
    }
    

    @Override
    public User getUserByUsername(String username) throws UserException {
    	
		return userDAO.findByUsername(username)
				.orElseThrow(() -> new UserException("User doesn't exist with username : " + username));
    }

    
    
    @Override
    public boolean isLoggedIn(User user) {
        return userTrackerService.isLoggedIn(user);
    }
    
    
    public boolean loginUser(User user) {
        return userTrackerService.loginUser(user);
    }

    
    @Override
    public boolean logoutUser(User user) {
        return userTrackerService.logoutUser(user);
    }

}
