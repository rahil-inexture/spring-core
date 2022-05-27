package com.spring.boot.services;

import com.spring.boot.entity.Type;

public class CalculateArea {
	//@Autowired
	SquareService squareService;
	//@Autowired
	RectangleService rectangleService;
	//@Autowired
	CircleService circleService;
	
	public CalculateArea(SquareService squareService, RectangleService rectangleService, CircleService circleService) {
		this.squareService = squareService;
		this.rectangleService = rectangleService;
		this.circleService = circleService;
	}



	public Double calculateArea(Type type, Double... r) {
		switch (type) {
		case RECTANGLE:
			if(r.length >= 2)
				return rectangleService.area(r[0], r[1]);
			else
				throw new RuntimeException("Missing rectangle params");
			
		case SQUARE:
			if(r.length == 1)
				return squareService.area(r[0]);
			else
				throw new RuntimeException("Missing square params");
			
		case CIRCLE:
			if(r.length == 1)
				return circleService.area(r[0]);
			else
				throw new RuntimeException("Missing circle params");
		default:
			throw new RuntimeException("Operation Not Support");
		}
	}
}
