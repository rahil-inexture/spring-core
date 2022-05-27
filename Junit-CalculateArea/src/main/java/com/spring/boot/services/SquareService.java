package com.spring.boot.services;

import org.springframework.stereotype.Service;

@Service
public class SquareService {

	public Double area(double r) {
		System.out.println("SquareService :"+ r * r);
		return r * r;
	}
}
