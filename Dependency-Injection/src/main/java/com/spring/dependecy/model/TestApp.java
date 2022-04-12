package com.spring.dependecy.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApp {
	private static ApplicationContext applicationContext;
	private static final Logger logger = LogManager.getLogger(TestApp.class);
	
	
	public static void main(String... args) {
		
		applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		DOMConfigurator.configure("log4j.xml");
		
		Manager managerObj =(Manager) applicationContext.getBean("manageAlias");
		managerObj.doWork();
		managerObj.callMetting();
		managerObj.printMList();
		
		
		Manager manageAlias =(Manager) applicationContext.getBean("manageAlias");
		logger.info(manageAlias.getMessage());
		
		
		
		logger.info("constructorOverloadObj Start Execution");
		ConstructorOverload constructorOverloadObj =(ConstructorOverload) applicationContext.getBean("constructor1");
		Object constructorOverloadObj2 = constructorOverloadObj.applicationContext().getBean("constructor2");
		
		logger.info("------------ Different Constructor Parameter ------------");
		logger.info("FIRST "+constructorOverloadObj.toString());
		logger.info("SECOND "+constructorOverloadObj2.toString());
		
		logger.info("Complete Execution..");
	}
}
