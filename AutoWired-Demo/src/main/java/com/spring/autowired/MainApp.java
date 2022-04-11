package com.spring.autowired;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.autowired.model.Employee;
import com.spring.autowired.model.EmployeeConstructor;

public class MainApp {
	private static ApplicationContext applicationContext;
	private static final Logger logger = LogManager.getLogger(MainApp.class);
	
	public static void main(String[] args) {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		DOMConfigurator.configure("log4j.xml");
		
		Employee employeeObj = (Employee) applicationContext.getBean("employee1");
		logger.info("	Employee Name :"+ employeeObj.getEmpName());
		logger.info("	Employee City :"+ employeeObj.getAddress().getCity());
		logger.info("	Employee State :"+ employeeObj.getAddress().getState());
	
		//using constructor through autowired
		logger.info("\n\n using constructor through autowired");
		EmployeeConstructor employeeConstructor = (EmployeeConstructor) applicationContext.getBean("employee2");
		logger.info("	Employee FullName :"+ employeeConstructor.getFullName());
		logger.info("	Employee department Name :"+ employeeConstructor.getDepartmentBean().getDepartmentName());
	
		//using byType through autowired
		logger.info("\n\n using byType autowired mode");
		EmployeeConstructor employeeConstructor2 = (EmployeeConstructor) applicationContext.getBean("employee3");
		logger.info("	Employee FullName :"+ employeeConstructor2.getFullName());
	}
}
