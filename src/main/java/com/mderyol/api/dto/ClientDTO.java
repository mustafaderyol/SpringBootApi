package com.mderyol.api.dto;

import java.util.List;

public class ClientDTO {

	private String clientId;
	private String clientSecret;;
	private List<String> authList;

	public ClientDTO(String clientId, String clientSecret, List<String> authList) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.authList = authList;
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

	public List<String> getAuthList() {
		return authList;
	}

	public void setAuthList(List<String> authList) {
		this.authList = authList;
	}

}
