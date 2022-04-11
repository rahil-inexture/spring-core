package com.spring.di.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.spring.di.services.Department;
import com.spring.di.services.Employee;

@Component
public class Company {
	
	private Employee employee;
	private Department department;
	
	
	@Autowired
	public Company(Employee employee) { // Constructor based DI
		this.employee = employee;
	}

	public void showEmployeeInfo() {
		employee.viewEmpInfo();
	}
	
	
	@Autowired
	public void setDepartment(Department department) { //setter injection
		this.department = department;
	}
	
	public void viewDeptInfo() {
		department.viewDeptName();
	}
}
