package com.mderyol.api.request;

public class RequestLoginByEmail {
	private String email;
	private String password;

	public RequestLoginByEmail() {
	}

	public RequestLoginByEmail(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
