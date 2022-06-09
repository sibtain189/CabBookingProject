package com.cab.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Driver extends User {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cab_id", referencedColumnName = "cabId")
	private Cab cab;

	@OneToMany(mappedBy = "driver")
	private List<TripBooking> tripBookings = new ArrayList<>();

	@NotNull
	private String dl;

	@Max(5)
	private Float rating;

}
