package com.spring.bean.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class MessageService {
	
	private Map<String, String> map;

	public MessageService() {
		map=new HashMap<>();	
	}
	
	public void sendMessage(String mailTo) {
		System.out.println("Sender "+ mailTo);
	}
	
	@PostConstruct
	public void init() {
		map.put("host", "mail.example.com");
      	map.put("port", "25");
      	map.put("from", "example@test.com");
      	System.out.println("Inside init method - "+map);
	}

	@PreDestroy
	public void destroy() {
		map.clear();
      	System.out.println("Inside destroy method - "+map);
	}
	
}
