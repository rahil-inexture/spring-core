package com.spring.di.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class EmployeeImpl  implements InitializingBean, DisposableBean,Employee{
	
	static Logger log = Logger.getLogger(DepartmentImpl.class);
	
	@Override
	public void viewEmpInfo() {
		log.info("Inside viewEmpInfo() method.");
	}

	@Override
	public void destroy() throws Exception {
		log.info("Employee Bean Destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("initializing Employee Bean");
	}
	
	
}
