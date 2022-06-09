package com.cab.service;

import com.cab.exceptions.CustomerException;
import com.cab.model.Customer;
import com.cab.model.TripBooking;

import java.util.List;


public interface CustomerServiceInterface {
	
    Customer insertCustomer(Customer customer);
    
    List<TripBooking> getList(Integer customerId) throws CustomerException;
    
    Customer updateCustomer(Customer customer, Integer customerId) throws CustomerException;

    Customer deleteCustomer(Integer customerId) throws CustomerException;

    List<Customer> viewCustomers();

    Customer viewCustomer(Integer customerId) throws CustomerException;

    Customer validateCustomer(String username, String password) throws CustomerException;

}
