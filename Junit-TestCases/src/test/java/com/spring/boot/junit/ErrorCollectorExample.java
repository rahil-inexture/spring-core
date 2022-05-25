package com.spring.boot.junit;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class ErrorCollectorExample {

	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();
	
	@Test
	public void testError() {
		errorCollector.addError(new Throwable("is an Error in first line"));
		errorCollector.addError(new Throwable("is an Error in second line"));
		
		System.out.println("Hello");
		try {
			Assert.assertTrue("A " == "B");
		} catch (Throwable e) {
			errorCollector.addError(e);
		}
		System.out.println("World!!");
	}
}
