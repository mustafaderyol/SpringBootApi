package com.mderyol.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import com.mderyol.api.security.CurrentUser;
import com.mderyol.api.security.UserPrincipal;
import com.mderyol.api.service.CustomUserDetailsService;

public class UserPrincipalController {

	@Autowired
	private CustomUserDetailsService userDetailService;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserDetails getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		return userDetailService.loadUserById(userPrincipal.getId());
	}

}
