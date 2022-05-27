package com.spring.boot.junit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.boot.controller.AreaController;
import com.spring.boot.services.CalculateAreaWithAutowired;
import com.spring.boot.services.CircleService;
import com.spring.boot.services.RectangleService;
import com.spring.boot.services.SquareService;

@Configuration
public class TestConfig {
	
	@Bean
	public AreaController areaController() {
		return new AreaController();
	}
	
	@Bean
	public CalculateAreaWithAutowired calculateAreaWithAutowired() {
		return new CalculateAreaWithAutowired();
	}
	
	@Bean
	public RectangleService rectangleService() {
		return new RectangleService();
	}
	
	@Bean
	public SquareService squareService() {
		return new SquareService();
	}
	
	@Bean
	public CircleService circleService() {
		return new CircleService();
	}
}
