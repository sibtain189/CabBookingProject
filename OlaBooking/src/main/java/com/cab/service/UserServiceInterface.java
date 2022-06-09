package com.cab.service;

import java.util.List;

import com.cab.exceptions.UserException;
import com.cab.model.User;

public interface UserServiceInterface {

	User saveUser(User user) throws UserException;

	User updateUser(Integer id, User user) throws UserException;

	User deleteUser(Integer id) throws UserException;

	List<User> getAllUser();

	User getUserById(Integer id) throws UserException;

	User getUserByUsername(String username) throws UserException;

	boolean isLoggedIn(User user);

	boolean loginUser(User user);

	boolean logoutUser(User user);
}
