package com.mderyol.api.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mderyol.api.dto.ClientDTO;
import com.mderyol.api.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	private Map<String, ClientDTO> clients = new HashMap<>();

	@PostConstruct
	public void init() {
		ClientDTO client1 = new ClientDTO("client1", "client1Secret", Arrays.asList("todo:add", "todo:delete"));
		clients.put(client1.getClientId() + "-" + client1.getClientSecret(), client1);
		ClientDTO client2 = new ClientDTO("client2", "client2Secret", Arrays.asList("todo:add"));
		clients.put(client2.getClientId() + "-" + client2.getClientSecret(), client2);
		ClientDTO client3 = new ClientDTO("client3", "client3Secret", Arrays.asList("todo:delete"));
		clients.put(client3.getClientId() + "-" + client3.getClientSecret(), client3);
	}

	public ClientDTO findByClient(String clientId, String clientSecret) throws UsernameNotFoundException {
		if (!clients.containsKey(clientId + "-" + clientSecret)) {
			new ResourceNotFoundException("Client", "clientId", clientId);
		}
		return clients.get(clientId + "-" + clientSecret);
	}

	public void addRole(String clientId, String role) {
		for (ClientDTO dto : clients.values()) {
			if (clientId.equals(dto.getClientId()) && !dto.getAuthList().contains(role)) {
				clients.get(dto.getClientId()).getAuthList().add(role);
			}
		}
	}

}
