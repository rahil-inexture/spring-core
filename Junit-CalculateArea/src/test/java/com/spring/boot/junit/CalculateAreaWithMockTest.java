package com.spring.boot.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.spring.boot.entity.Type;
import com.spring.boot.services.CalculateAreaWithAutowired;
import com.spring.boot.services.CircleService;
import com.spring.boot.services.RectangleService;
import com.spring.boot.services.SquareService;

public class CalculateAreaWithMockTest {
	@Mock
	SquareService squareService;
	
	//@Spy
	@Mock
	RectangleService rectangleService;
	@Mock
	CircleService circleService;
	
	@InjectMocks
	CalculateAreaWithAutowired calculateAreaWithAutowired;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void calculateRectangeAreaTest() {
		Mockito.when(rectangleService.area(5.0d, 4.0d)).thenReturn(20d);
		Double calculateArea = this.calculateAreaWithAutowired.calculateArea(Type.RECTANGLE, 5.0d, 4.0d);
		Assert.assertEquals(new Double(20d), calculateArea);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void calculateSquareAreaTest() {
		Mockito.when(squareService.area(5.0d)).thenReturn(25d);
		Double calculateArea = this.calculateAreaWithAutowired.calculateArea(Type.SQUARE, 5.0d);
		Assert.assertEquals(new Double(25d), calculateArea);
	}
		
	@SuppressWarnings("deprecation")
	@Test
	public void calculateCircleAreaTest() {
		Mockito.when(circleService.area(5.0d)).thenReturn(78.53981634d);
		Double calculateArea = this.calculateAreaWithAutowired.calculateArea(Type.CIRCLE, 5.0d);
		Assert.assertEquals(new Double(78.53981634d), calculateArea);
	}
}
