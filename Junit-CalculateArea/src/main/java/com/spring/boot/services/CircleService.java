package com.spring.boot.services;

import org.springframework.stereotype.Service;

@Service
public class CircleService {
	
	public Double area(double r) {
		return Math.PI * r * r;
	}
}
