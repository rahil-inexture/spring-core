package com.spring.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoPostConstructWithAppPropertyUseApplication {

	@Value("${myapp.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(DemoPostConstructWithAppPropertyUseApplication.class, args);
	}

	@PostConstruct
	private void defaultOperationExecute(){
		System.out.println("Welcome To: "+ appName);
	}
}
