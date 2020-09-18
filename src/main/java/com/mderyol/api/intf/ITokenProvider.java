package com.mderyol.api.intf;

import org.springframework.security.core.Authentication;

public interface ITokenProvider {

	String createToken(Authentication authentication);

	Long getUserIdFromToken(String token);

	boolean validateToken(String authToken);

}
