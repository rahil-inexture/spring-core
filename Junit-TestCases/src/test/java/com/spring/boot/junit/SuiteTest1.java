package com.spring.boot.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class SuiteTest1 {
	
	String message = "Saurabh";
	
	JUnitMessage junitMessage = new JUnitMessage(message);
	
	
	 @Test(expected = ArithmeticException.class)
	 public void testJunitMessage() {
		 System.out.println("Junit Message is Printing");
		 junitMessage.printMessage();
	 }
	 
	 @Test(expected = ArithmeticException.class)
	 public void testJunitHiMessage() {
		 message = "Hi.." + message;
		 System.out.println("Junit Hi Message is Printing");
		 assertEquals(message, junitMessage.printHiMessage());
		 System.out.println("Suite Test 2 is successful " + message); 
	 }
}
