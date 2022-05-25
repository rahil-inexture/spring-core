package com.spring.boot.services;

import org.springframework.stereotype.Service;

@Service
public class RectangleService {
	
	public Double area(double r, double h) {
		log();
		return r * h;
	}

	private void log() {
		System.out.println("skip this");
	}
}
