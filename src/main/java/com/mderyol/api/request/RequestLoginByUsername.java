package com.mderyol.api.request;

public class RequestLoginByUsername {
	private String username;
	private String password;

	public RequestLoginByUsername() {
	}

	public RequestLoginByUsername(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
