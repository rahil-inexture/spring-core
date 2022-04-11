package com.spring.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.spring.di.services.Department;
import com.spring.di.services.DepartmentImpl;
import com.spring.di.services.Employee;
import com.spring.di.services.EmployeeImpl;

@Configuration
@ComponentScan(basePackages="com.spring.di")
public class AppConfiguration {
	
	@Bean
	public Employee getEmployee() {
		return new EmployeeImpl();
	}
	
	@Bean
	public Department getDepartment() {
		return new DepartmentImpl();
	}
}
