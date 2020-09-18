package com.mderyol.api.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mderyol.api.dto.UserDTO;
import com.mderyol.api.exceptions.OAuth2AuthenticationException;
import com.mderyol.api.security.UserPrincipal;
import com.mderyol.api.security.oauth2.user.OAuth2UserInfo;
import com.mderyol.api.security.oauth2.user.OAuth2UserInfoFactory;
import com.mderyol.api.service.CustomUserDetailsService;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private CustomUserDetailsService userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		try {
			return processOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
				oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
		if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
		}
		UserDTO user = userRepository.findByEmail(oAuth2UserInfo.getEmail());
		return UserPrincipal.create(user, oAuth2User.getAttributes());
	}
}
