package com.cab.model;

public enum CabType {
	
	MICRO, MINI, PRIME, AUTO, LUXURY;

	double price;

	public void setPrice(double price) {
		this.price = price;
	}

	public int sittingCapacity() {
		switch (this) {
		case MICRO:
			return 2;
		case MINI:
			return 3;
		case PRIME:
			return 4;
		case AUTO:
			return 2;
		case LUXURY:
			return 4;
		default:
			return 4;
		}
	}

	public double getPrice() {
		price = 2;
		switch (this) {
		case AUTO:
			return price *= 1.15;
		case MICRO:
			return price *= 1.5;
		case MINI:
			return price *= 1.75;
		case PRIME:
			return price *= 2;
		case LUXURY:
			return price *= 2.5;
		default:
			return price;
		}
	}
}
