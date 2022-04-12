package com.spring.dependecy.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Accountant implements Employee{

	final Logger logger = LogManager.getLogger(Manager.class);
	
	@Override
	public void doWork() {
		logger.info("audit work ...");
	}
}
