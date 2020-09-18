package com.mderyol.api.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mderyol.api.dto.UserDTO;
import com.mderyol.api.enumeration.AuthProvider;
import com.mderyol.api.exceptions.ResourceNotFoundException;
import com.mderyol.api.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private Map<String, UserDTO> users = new HashMap<>();

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() {
		UserDTO user1 = new UserDTO(1L, "mustafa.deryol@hotmail.com", passwordEncoder.encode("123"),
				AuthProvider.facebook, Arrays.asList("todo:add", "todo:delete"));
		users.put(user1.getEmail(), user1);
		UserDTO user2 = new UserDTO(2L, "info@azerion.com", passwordEncoder.encode("123"), AuthProvider.local,
				Arrays.asList("todo:add"));
		users.put(user2.getEmail(), user2);
		UserDTO user3 = new UserDTO(3L, "fikret@hotmail.com", passwordEncoder.encode("123"), AuthProvider.local,
				Arrays.asList("todo:delete"));
		users.put(user3.getEmail(), user3);
	}

	public UserDTO findByEmail(String email) throws UsernameNotFoundException {
		if (!users.containsKey(email)) {
			new ResourceNotFoundException("User", "email", email);
		}
		return users.get(email);
	}

	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		if (id == null) {
			new ResourceNotFoundException("User", "id", id);
		}
		UserDTO user = null;
		for (UserDTO dto : users.values()) {
			if (id.equals(dto.getId())) {
				user = dto;
			}
		}
		if (user == null) {
			new ResourceNotFoundException("User", "id", id);
		}
		return UserPrincipal.create(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!users.containsKey(username)) {
			new ResourceNotFoundException("User", "email", username);
		}
		return UserPrincipal.create(users.get(username));
	}

	public void addRole(Long id, String role) {
		for (UserDTO dto : users.values()) {
			if (id.equals(dto.getId()) && !dto.getAuthList().contains(role)) {
				users.get(dto.getEmail()).getAuthList().add(role);
			}
		}
	}
}
