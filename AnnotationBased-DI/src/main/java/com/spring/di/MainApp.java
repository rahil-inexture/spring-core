package com.spring.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.di.model.Company;

public class MainApp {
	
	public static void main(String... args) {
	      AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
	      Company companyObj = appContext.getBean(Company.class);
	      companyObj.showEmployeeInfo();
	      companyObj.viewDeptInfo();
	      appContext.close();
	}
}
