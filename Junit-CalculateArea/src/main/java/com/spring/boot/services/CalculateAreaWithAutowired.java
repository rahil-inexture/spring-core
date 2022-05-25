package com.spring.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.boot.entity.Type;

@Component
public class CalculateAreaWithAutowired {

	@Autowired
	SquareService squareService;
	@Autowired
	RectangleService rectangleService;
	@Autowired
	CircleService circleService;

	public Double calculateArea(Type type, Double... r) {
		switch (type) {
		case RECTANGLE:
			if (r.length >= 2)
				return rectangleService.area(r[0], r[1]);
			else
				throw new RuntimeException("Missing required params");

		case SQUARE:
			if (r.length >= 1)
				return squareService.area(r[0]);
			else
				throw new RuntimeException("Missing required params");

		case CIRCLE:
			if (r.length >= 1)
				return circleService.area(r[0]);
			else
				throw new RuntimeException("Missing required params");
		default:
			throw new RuntimeException("Operation Not Support");
		}
	}
}
