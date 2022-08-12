package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.example.demo","com.example.model","com.example.controller","com.example.service","com.example.repository","com.example.config"})
@EntityScan(basePackages={"com.example.demo","com.example.model","com.example.controller","com.example.service","com.example.repository","com.example.config"})
@EnableMongoRepositories ("com.example.repository")
public class SpringpocApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringpocApplication.class, args);
	}

}
