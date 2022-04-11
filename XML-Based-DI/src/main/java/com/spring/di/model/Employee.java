package com.spring.di.model;

import java.util.List;
import java.util.Map;

public class Employee {
	
	private int empId;
	private String empName;
	private List<CurrentAddress> currentAddress;
	private List<String> vehicleLst;
	private Map<String, String> empPerformance;
	private Department department;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public String getEmpDepartment() {
		return this.department.getDeptName();
	}
	
	public Employee(List<CurrentAddress> currentAddress, List<String> vehicleLst, Map<String, String> empPerformance) {
		this.currentAddress = currentAddress;
		this.vehicleLst = vehicleLst;
		this.empPerformance = empPerformance;
	}
	
	public List<CurrentAddress> getEmployeeAddress() {
		return this.currentAddress;
	}
	
	public List<String> getEmployeeVehicleLst() {
		return vehicleLst;
	}
	
	public Map<String, String> getEmployeePerformance() {
		return empPerformance;
	}
}
