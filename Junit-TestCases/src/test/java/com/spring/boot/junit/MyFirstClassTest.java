package com.spring.boot.junit;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyFirstClassTest {
	
	@Test
	public void myFirstMethod() {
		String str = "Junit is working fine.";
		assertEquals("Junit is working fine.", str);
		
	}
}
