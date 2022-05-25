package com.spring.boot.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.spring.boot.entity.Type;
import com.spring.boot.services.CalculateArea;
import com.spring.boot.services.CircleService;
import com.spring.boot.services.RectangleService;
import com.spring.boot.services.SquareService;


public class CalculateAreaTest {
	SquareService squareService;
	RectangleService rectangleService;
	CircleService circleService;
	
	CalculateArea calculateArea;
	
	
	@Before
	public void init() {
		rectangleService = Mockito.mock(RectangleService.class);
		squareService = Mockito.mock(SquareService.class);
		circleService = Mockito.mock(CircleService.class);
		
		calculateArea = new CalculateArea(squareService, rectangleService, circleService);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void calculateRectangeAreaTest() {
		Mockito.when(rectangleService.area(5.0d, 4.0d)).thenReturn(20d);
		Double calculateArea = this.calculateArea.calculateArea(Type.RECTANGLE, 5.0d, 4.0d);
		Assert.assertEquals(new Double(20d), calculateArea);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void calculateSquareAreaTest() {
		Mockito.when(squareService.area(5.0d)).thenReturn(25d);
		Double calculateArea = this.calculateArea.calculateArea(Type.SQUARE, 5.0d);
		Assert.assertEquals(new Double(25d), calculateArea);
	}
		
	@SuppressWarnings("deprecation")
	@Test
	public void calculateCircleAreaTest() {
		Mockito.when(circleService.area(5.0d)).thenReturn(78.53981634d);
		Double calculateArea = this.calculateArea.calculateArea(Type.CIRCLE, 5.0d);
		Assert.assertEquals(new Double(78.53981634d), calculateArea);
	}
}
