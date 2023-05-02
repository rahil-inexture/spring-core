package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.util.DefaultPropertiesPersister;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Properties;

@Component
public class PropertiesConfig {

    @PostConstruct
    public void setDefaultProperties() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("connection.database", "mysql");
        properties.setProperty("connection.hostname", "localhost");
        properties.setProperty("connection.username", "root");
        properties.setProperty("connection.password", "root");

        File file = new File("src/main/resources/application.properties");
        FileInputStream fis=new FileInputStream(file);
        properties.load(fis);
        OutputStream outputStream = new FileOutputStream( file );
        DefaultPropertiesPersister defaultPropertiesPersister = new DefaultPropertiesPersister();
        defaultPropertiesPersister.store(properties, outputStream, "Comment");
    }
}
