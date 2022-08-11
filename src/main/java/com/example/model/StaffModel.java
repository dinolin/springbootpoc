package com.example.model;

import org.springframework.stereotype.Component;

@Component
public class StaffModel {
	
	 private int id;
	  private String email;
	  private String phone;
	  private String password;
	  private String position;
	  
	  public int getId() {
		return id;
	  }
	  
	  public void setId(int id) {
		this.id = id;
	  }
	  public String getEmail() {
		return email;
	  }
	  
	  public void setEmail(String email) {
		this.email = email;
	  }
	  
	  public String getPhone() {
		return phone;
	  }
	  
	  public void setPhone(String phone) {
		this.phone = phone;
	  }
	  
	  public String getPassword() {
		return password;
	  }
	  
	  public void setPassword(String password) {
		this.password = password;
	  }
	  
	  public String getPosition() {
		return position;
	  }
	  
	  public void setPosition(String position) {
		this.position = position;
	  }
	  
	  

}