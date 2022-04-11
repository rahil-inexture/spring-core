package com.spring.autowired.model;

public class EmployeeConstructor {
	
	private String fullName;
	   
	  public EmployeeConstructor(DepartmentBean departmentBean)
	  {
	    this.departmentBean = departmentBean;
	  }
	   
	  private DepartmentBean departmentBean;
	 
	  public DepartmentBean getDepartmentBean() {
	    return departmentBean;
	  }
	 
	  public void setDepartmentBean(DepartmentBean departmentBean) {
	    this.departmentBean = departmentBean;
	  }
	 
	  public String getFullName() {
	    return fullName;
	  }
	 
	  public void setFullName(String fullName) {
	    this.fullName = fullName;
	  }
}
