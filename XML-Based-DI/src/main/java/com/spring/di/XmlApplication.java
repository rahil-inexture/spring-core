package com.spring.di;

import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.di.model.Employee;

public class XmlApplication {
	
	private static ApplicationContext applicationContext;
	private static final Logger logger = LogManager.getLogger(XmlApplication.class);
	
	public static void main(String[] args) {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		DOMConfigurator.configure("log4j.xml");
		
		Employee employeeObj = (Employee) applicationContext.getBean("employee1");
		String vehicleCollections = employeeObj.getEmployeeVehicleLst().stream().sorted().map(String::toUpperCase).collect(Collectors.joining(", "));
		logger.info("----------------------- Employee Details  -----------------------");
		logger.info("	Employee ID :"+ employeeObj.getEmpId());
		logger.info("	Employee Name :"+ employeeObj.getEmpName());
		logger.info("	Employee Department :"+ employeeObj.getEmpDepartment());
		logger.info("	Employee Address :"+ employeeObj.getEmployeeAddress().stream().collect(Collectors.toList()));
		logger.info("	Employee Perfomance : "+ employeeObj.getEmpDepartment());
		logger.info("	Employee Vehicle Collection :" + vehicleCollections);
		logger.info("-----------------------// END  -----------------------");
	
		//employeeObj.getEmployeeAddress().stream().map((val)->val).collect(Collectors.toList()).forEach((val)-> System.out.print(val));
	}
}
