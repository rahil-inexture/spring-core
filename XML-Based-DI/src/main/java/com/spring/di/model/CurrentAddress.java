package com.spring.di.model;

public class CurrentAddress{
	
	private String city;
	private String State;
	private String house;
	private int pinCode;
		
	public CurrentAddress(String city, String state, String house, int pinCode) {
		this.city = city;
		State = state;
		this.house = house;
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "[city=" + city + ", State=" + State + ", house=" + house + ", pinCode=" + pinCode + "]\n\t";
	}
}
