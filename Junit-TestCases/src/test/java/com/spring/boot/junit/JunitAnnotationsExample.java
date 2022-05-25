package com.spring.boot.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class JunitAnnotationsExample {

	private ArrayList<String> list;

	@BeforeClass
	public static void method1() {
		System.out.println("Using @BeforeClass , executed before all test cases ");
	}

	// before method
	@Before
	public void method2() {
		list = new ArrayList<String>();
		System.out.println("Using @Before annotations, executed before each test cases");
	}

	// AfterClass
	@AfterClass
	public static void method3() {
		System.out.println("Using @AfterClass annotations, executed after all the test cases");
	}

	// @After
	@After
	public void method4() {
		list.clear();
		System.out.println("Using @After, executed after each test case");
	}

	// @Test
	@Test
	public void method5() {
		list.add("test");
		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
	}

	// @Ignore
	@Ignore
	public void method6() {
		System.out.println("Using @Ignore, this execution is ignored");
	}

	// @Test service or method response specific time duration
	@Test(timeout = 10)
	public void method7() {
		System.out.println("Using @Test, this execution is 10 second");
	}

	// @Test service or method response specific time duration
	@Test(expected = NoSuchMethodException.class)
	public void method8() {
		System.out.println("Using @Test(expected), it will check for specified exception during its execution");
	}	
}
