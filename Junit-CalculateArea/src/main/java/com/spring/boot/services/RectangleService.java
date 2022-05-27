package com.spring.boot.services;

import org.springframework.stereotype.Service;

@Service
public class RectangleService {
	
	public Double area(double r, double h) {
		log();
		System.out.println("RectangleService :"+ r * h);
		return r * h;
	}

	private void log() {
		System.out.println("skip this");
	}
}
