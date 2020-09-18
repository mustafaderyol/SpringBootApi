package com.mderyol.api.request;

public class RequestClient {
	private String clientId;
	private String clientSecret;
	private String name;

	public RequestClient() {

	}

	public RequestClient(String clientId, String clientSecret, String name) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.name = name;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
