package com.mderyol.api.security.oauth2.user;

import java.util.Map;

public class LocalOAuth2UserInfo extends OAuth2UserInfo {
	public LocalOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return (String) attributes.get("id");
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}
}
