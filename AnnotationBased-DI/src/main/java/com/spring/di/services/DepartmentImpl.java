package com.spring.di.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class DepartmentImpl implements InitializingBean, DisposableBean,Department{

	static Logger log = Logger.getLogger(DepartmentImpl.class);
	
	@Override
	public void viewDeptName() {
		log.info("Software Department");
	}

	@Override
	public void destroy() throws Exception {
		log.info("Department Bean Destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("initializing Department Bean");
	}

}
