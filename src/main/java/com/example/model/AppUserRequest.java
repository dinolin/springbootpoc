package com.example.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class AppUserRequest {
	   @NotBlank
	    private String emailAddress;
	    @NotBlank
	    private String password;
	    @NotBlank
	    private String name;
	    @NotEmpty
	    private List<UserAuthorityModel> authorities;

	    public String getEmailAddress() {
	        return emailAddress;
	    }

	    public void setEmailAddress(String emailAddress) {
	        this.emailAddress = emailAddress;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public List<UserAuthorityModel> getAuthorities() {
	        return authorities;
	    }

	    public void setAuthorities(List<UserAuthorityModel> authorities) {
	        this.authorities = authorities;
	    }
}
