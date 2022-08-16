package com.example.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class AppUserModel {
    private String id;
    private String emailAddress;
    private String password;
    private String name;
    private List<UserAuthorityModel> authorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
