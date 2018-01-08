package com.wwa.dto;

public class User {
	private String username;
	private String mobile;
	private String email;

	public User() {
		super();
	}

	public User(String username, String mobile, String email) {
		this.username = username;
		this.mobile = mobile;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
