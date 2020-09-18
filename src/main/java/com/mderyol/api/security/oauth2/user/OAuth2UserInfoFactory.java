package com.mderyol.api.security.oauth2.user;

import java.util.Map;

import com.mderyol.api.enumeration.AuthProvider;
import com.mderyol.api.exceptions.OAuth2AuthenticationException;

public class OAuth2UserInfoFactory {

	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
			return new FacebookOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(AuthProvider.local.toString())) {
			return new LocalOAuth2UserInfo(attributes);
		} else {
			throw new OAuth2AuthenticationException("Sorry! Login with " + registrationId + " is not supported yet.");
		}
	}
}
