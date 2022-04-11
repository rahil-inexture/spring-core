package com.spring.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.bean.service.MessageService;

public class MainApplication {
	public static void main(String[] args) {
	      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	      
	      // Send mail 1
	      MessageService mailService1 = context.getBean(MessageService.class);
	      mailService1.sendMessage("rahil@example.com");

	      // Send mail 2
	      MessageService mailService2 = context.getBean(MessageService.class);
	      mailService2.sendMessage("hello@example.com");

	      context.close();
	 }
}
