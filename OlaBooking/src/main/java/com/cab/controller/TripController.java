package com.cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cab.model.TripBooking;
import com.cab.service.TripServiceInterface;

@RestController
@RequestMapping("/cabook/trips")
public class TripController {
	@Autowired
	private TripServiceInterface tripServiceInterface;

	@PostMapping("/{c}/{d}")
	ResponseEntity<TripBooking> saveTrip(@RequestBody TripBooking tripBooking, @PathVariable("c") Integer customerId,
			@PathVariable("d") Integer driverId) {
		TripBooking trip1 = tripServiceInterface.addTrip(tripBooking, customerId, driverId);
		return new ResponseEntity<>(trip1, HttpStatus.ACCEPTED);
	}


	@GetMapping("/{id}")
	ResponseEntity<?> getTripById(@PathVariable("id") Integer id) {
		TripBooking tripBooking = tripServiceInterface.getTripById(id);
		return new ResponseEntity<>(tripBooking, HttpStatus.OK);
	}

	@GetMapping("/bill/{id}")
	ResponseEntity<Double> calculateBill(@PathVariable("id") Integer customerId) {
		Double bill = tripServiceInterface.calculateBill(customerId);
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	ResponseEntity<TripBooking> updateTrip(@PathVariable("id") Integer id, @RequestBody TripBooking tripBooking) {
		TripBooking trip1 = tripServiceInterface.updateTrip(id, tripBooking);
		return new ResponseEntity<>(trip1, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<TripBooking> deleteTrip(@PathVariable("id") Integer id) {
		TripBooking tripBooking = tripServiceInterface.deleteTrip(id);
		return new ResponseEntity<>(tripBooking, HttpStatus.OK);
	}
}
