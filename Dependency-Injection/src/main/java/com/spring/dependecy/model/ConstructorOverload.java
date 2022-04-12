package com.spring.dependecy.model;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ConstructorOverload implements ApplicationContextAware{
	private String companyName;
	private int employeeLength;
	private String companyLocation;
	private ApplicationContext applicationContext;
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getEmployeeLength() {
		return employeeLength;
	}
	public void setEmployeeLength(int employeeLength) {
		this.employeeLength = employeeLength;
	}
	public String getCompanyLocation() {
		return companyLocation;
	}
	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}
	
	public ConstructorOverload(String companyName, int employeeLength, String companyLocation) {
		super();
		this.companyName = companyName;
		this.employeeLength = employeeLength;
		this.companyLocation = companyLocation;
	}
	
	
	public ConstructorOverload(String companyName, String companyLocation) {
		super();
		this.companyName = companyName;
		this.companyLocation = companyLocation;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public ApplicationContext applicationContext() {
		return applicationContext;
	}
	
	@Override
	public String toString() {
		return "ConstructorOverload [companyName=" + companyName + ", employeeLength=" + employeeLength
				+ ", companyLocation=" + companyLocation + "]";
	}	
}
