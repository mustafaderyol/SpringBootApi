package com.mderyol.api.exceptions;

import org.springframework.security.core.AuthenticationException;

public class OAuth2AuthenticationException extends AuthenticationException {
	/**
	 *
	 */
	private static final long serialVersionUID = 9062688723390118282L;

	public OAuth2AuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}

	public OAuth2AuthenticationException(String msg) {
		super(msg);
	}
}
