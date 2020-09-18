package com.mderyol.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.mderyol.api.dto.ClientDTO;
import com.mderyol.api.dto.UserDTO;

@Component("RoleService")
public class RoleService {

	@Autowired
	CustomUserDetailsService userService;

	@Autowired
	ClientService clientService;

	public boolean hasRole(String email, String role) {
		UserDTO dto = userService.findByEmail(email);
		if (!CollectionUtils.isEmpty(dto.getAuthList())) {
			for (String userRole : dto.getAuthList()) {
				if (role.equals(userRole)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasRoleForClient(String clientId, String clientSecret, String role) {
		ClientDTO dto = clientService.findByClient(clientId, clientSecret);
		if (dto == null) {
			return false;
		}
		if (!CollectionUtils.isEmpty(dto.getAuthList())) {
			for (String userRole : dto.getAuthList()) {
				if (role.equals(userRole)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasClient(String clientId, String clientSecret) {
		ClientDTO dto = clientService.findByClient(clientId, clientSecret);
		if (dto == null) {
			return false;
		}
		return true;
	}
}
