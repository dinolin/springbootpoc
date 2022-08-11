package com.example.model;
import org.springframework.stereotype.Component;

@Component
public class CompanyModel {

	private int id;
	private String name;
	  
	  public int getId() {
		return id;
	  }
	  
	  public void setId(int id) {
		this.id = id;
	  }
	  public String getName() {
		return name;
	  }
	  
	  public void setName(String name) {
		this.name = name;
	  }
	  
}