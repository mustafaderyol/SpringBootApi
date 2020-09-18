package com.mderyol.api.dto;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {

	private static final long serialVersionUID = -9203529103463459697L;
	private String authority;

	public UserRole(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}
}
