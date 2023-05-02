package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.util.DefaultPropertiesPersister;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Properties;

@SpringBootApplication
public class DemoApplication {


	private static String database;
	private static String password;

	@Value("${connection.database}")
	public void setDatabase(String database){
		this.database = database;
	}

	@Value("${connection.password}")
	public void setPassword(String password){
		this.password = password;
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);

		System.out.println("database=" + database);
		System.out.println("password=" + password);
	}


}