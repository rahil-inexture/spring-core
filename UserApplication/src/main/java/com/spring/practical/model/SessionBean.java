package com.spring.practical.model;

import java.io.Serializable;

public class SessionBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String username;
	private String email;
	private Long userType;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getUserType() {
		return userType;
	}
	public void setUserType(Long userType) {
		this.userType = userType;
	}	
}
