package com.spring.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.spring.bean")
@PropertySource(value="classpath:project.properties")
public class AppConfig {
	
}
