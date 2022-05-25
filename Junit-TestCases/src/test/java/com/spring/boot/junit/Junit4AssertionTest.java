package com.spring.boot.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class Junit4AssertionTest {

	@Test
	public void testAssert() {
		
		//variable declaration
		String str1 = "Junit";
		String str2 = "Junit";
		String str3 = "test";
		String str4 = "test";
		String str5 = null;
		
		int var1 = 1;
		int var2 = 2;
		int []airArr1 = {1,2,3};
		int []airArr2 = {1,2,3};
		
		
		//Assert statements
		assertEquals(str1, str2);
		assertSame(1, var1);
		assertSame(str3, str4);
		assertNotSame(str1, str3);
		assertNotNull(str1);
		assertNull(str5);
		assertArrayEquals(airArr1, airArr2);
		assertTrue(var1 < var2);
		assertFalse(var1 == var2);
		//assertDoesNotThrow(executable, messageSupplier);
		//assertLinesMatch(expectedLines, actualLines);
		//assertInstanceOf(expectedType, actualValue)
	}
}
