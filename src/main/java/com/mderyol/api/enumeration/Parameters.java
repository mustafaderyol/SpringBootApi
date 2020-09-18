package com.mderyol.api.enumeration;

public enum Parameters {
	CLIENT_ID("clientId"), CLIENT_SECRET("clientSecret"), AUTH_ID("x-auth-id");

	Parameters(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return this.name;
	}
}