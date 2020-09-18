package com.mderyol.api.dto;

import java.util.List;

import com.mderyol.api.enumeration.AuthProvider;

public class UserDTO {
	private Long id;
	private String email;
	private String password;
	private AuthProvider provider;
	private List<String> authList;

	public UserDTO(Long id, String email, String password, AuthProvider provider, List<String> authList) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.provider = provider;
		this.authList = authList;
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

	public AuthProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getAuthList() {
		return authList;
	}

	public void setAuthList(List<String> authList) {
		this.authList = authList;
	}

}
