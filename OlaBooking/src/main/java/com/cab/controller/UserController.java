package com.cab.controller;

import java.util.List;

import com.cab.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cab.model.User;
import com.cab.service.UserServiceInterface;

// TODO: Completely remove User Controller

@RestController
@RequestMapping("cabook/users")
public class UserController {

	
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	

	@PostMapping("/")
	public User saveUserHandler(@RequestBody User user) {
		
		return userServiceInterface.saveUser(user);
	}
	
	

	@GetMapping("/")
	public List<User> getAllUserHandler() {
		
		return userServiceInterface.getAllUser();
	}
	
	

	@GetMapping("/{id}")
	public User getUserHandler(@PathVariable("id") Integer id) {
		
		return userServiceInterface.getUserById(id);
	}

	
	
	@PutMapping("/{id}")
	public User updateUserHandler(@PathVariable("id") Integer id, @RequestBody User user) {
		
		return userServiceInterface.updateUser(id, user);
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserHandler(@PathVariable("id") Integer id) {
		
		userServiceInterface.deleteUser(id);
		
		return new ResponseEntity<>("User deleted with id!" + id, HttpStatus.OK);
	}

	
	
	@PostMapping("/login")
	public ResponseEntity<String> userLoginHandler(@RequestBody User user) {
//        if (curUser.getPassword().equals(password))
//            return new ResponseEntity<>("Login success!", HttpStatus.OK);
//        else
//            return new ResponseEntity<>("Password not matched!", HttpStatus.UNAUTHORIZED);

		try {
			boolean loginSuccessful = userServiceInterface.loginUser(user);
			
			if (loginSuccessful)
				return new ResponseEntity<>("login successful", HttpStatus.OK);
			
			return new ResponseEntity<>("passwords don't match", HttpStatus.UNAUTHORIZED);
			
		} catch (UserException e) {
			
			return new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);
		}
	}
	
	

	@GetMapping("/login/{id}")
	public boolean isLoggedIn(@PathVariable("id") Integer id) {
		User user = new User();
		user.setUserId(id);
		return userServiceInterface.isLoggedIn(user);
	}

	@GetMapping("/logout/{id}")
	public ResponseEntity<String> logout(@PathVariable("id") Integer id) {
		User user = new User();
		user.setUserId(id);
		boolean successful = userServiceInterface.logoutUser(user);
		if (successful)
			return new ResponseEntity<>("User logged out successfully", HttpStatus.OK);
		return new ResponseEntity<>("User not logged in", HttpStatus.BAD_REQUEST);
	}
}
