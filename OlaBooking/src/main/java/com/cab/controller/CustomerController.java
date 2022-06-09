package com.cab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cab.exceptions.CustomerException;
import com.cab.model.Customer;
import com.cab.model.TripBooking;
import com.cab.model.TripStatus;
import com.cab.model.UserRoles;
import com.cab.service.CustomerServiceInterface;
import com.cab.service.TripServiceInterface;

@RestController
@RequestMapping("/cabook/customers")
public class CustomerController {

	@Autowired
	private CustomerServiceInterface customerServiceInterface;

	@Autowired
	private TripServiceInterface tripServiceInterface;

	@PostMapping("/")
	public Customer insertCustomer(@RequestBody Customer customer) {

		customer.setRoles(List.of(UserRoles.CUSTOMER));

		return customerServiceInterface.insertCustomer(customer);

	}

	@GetMapping("/")
	public List<Customer> viewCustomers() {

		return customerServiceInterface.viewCustomers();

	}

	@GetMapping("/{id}")
	public Customer viewCustomer(@PathVariable("id") Integer id) throws CustomerException {

		return customerServiceInterface.viewCustomer(id);

	}

	@PutMapping("/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("id") Integer id)
			throws CustomerException {

		return customerServiceInterface.updateCustomer(customer, id);

	}

	@DeleteMapping("/{id}")
	public Customer deleteCustomer(@PathVariable("id") Integer id) {

		return customerServiceInterface.deleteCustomer(id);

	}

	@PostMapping("/booktrip")
	public ResponseEntity<?> booktrip(@RequestParam("customerid") Integer customerId,
			@RequestParam("driverid") Integer driverId, @RequestBody TripBooking tripBooking) {

		TripBooking trip1 = tripServiceInterface.addTrip(tripBooking, customerId, driverId);

		return new ResponseEntity<>(trip1, HttpStatus.ACCEPTED);

	}

	@PatchMapping("/canceltrip/{tripId}")
	public ResponseEntity<?> cancelBooking(@PathVariable("tripId") Integer tripId) {
		
		TripBooking tripBooking = tripServiceInterface.getTripById(tripId);
		
		tripBooking.setStatus(TripStatus.CANCELED);
		
		tripServiceInterface.updateTrip(tripId, tripBooking);
		
		return new ResponseEntity<>("Booking cancelled", HttpStatus.ACCEPTED);
		
	}

}
