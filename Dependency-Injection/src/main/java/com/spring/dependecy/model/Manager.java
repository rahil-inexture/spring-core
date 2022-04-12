package com.spring.dependecy.model;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Manager implements Employee, InitializingBean, DisposableBean{
	final Logger logger = LogManager.getLogger(Manager.class);
	
	Accountant accountant;
	private List<String> mList;
	String message;
	
	public String getMessage() {
		 return message;
	}

	public void setMessage(String message) {
	  this.message = message;
	}
	
	
	public List<String> getmList() {
		return mList;
	}

	public void setmList(List<String> mList) {
		this.mList = mList;
	}

	public Manager(Accountant accountant) {
		logger.info("Manager Constructor...");
		this.accountant = accountant;
	}
	
	@Override
	public void doWork() {
		logger.info("Manage all the Accountants");
	}
	
	public void callMetting() {
		accountant.doWork();
	}
	
	public void printMList() {
		logger.info(this.mList.toString());
	}

	@Override
	public void destroy() throws Exception {
		logger.info("Account class cleanup activity");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("");
	}
}
