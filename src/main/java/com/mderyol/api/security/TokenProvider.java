package com.mderyol.api.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mderyol.api.config.AppProperties;
import com.mderyol.api.intf.ITokenProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider implements ITokenProvider {

	private AppProperties appProperties;

	public TokenProvider(AppProperties appProperties) {
		this.appProperties = appProperties;
	}

	@Override
	public String createToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

		return Jwts.builder().setSubject(Long.toString(userPrincipal.getId())).setIssuedAt(new Date())
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
				.compact();
	}

	@Override
	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token)
				.getBody();

		return Long.parseLong(claims.getSubject());
	}

	@Override
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
			return true;
		} catch (Exception ex) {
		}
		return false;
	}

}
