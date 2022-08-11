package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SpringBootHelloWorld {

	
	@GetMapping("/X")
	public String hello(){
		return "Hey, Spring Boot 的 Hello World ! ";
	}
	
	@GetMapping("/index")
	public String helloIndex(){
		return "index";
	}
}