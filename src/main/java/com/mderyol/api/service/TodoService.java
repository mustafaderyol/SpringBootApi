package com.mderyol.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mderyol.api.dto.TodoDTO;
import com.mderyol.api.exceptions.InvalidTodoException;
import com.mderyol.api.intf.ITodoService;
import com.mderyol.api.utils.Util;

@Service
public class TodoService implements ITodoService {
	static Map<Long, TodoDTO> endUserMap = new HashMap<>();

	public TodoService() {
		TodoDTO user1 = new TodoDTO();
		user1.setId(101L);
		user1.setName("Yapılacak 1");
		endUserMap.put(user1.getId(), user1);
		TodoDTO user2 = new TodoDTO();
		user2.setId(102L);
		user2.setName("Yapılacak 2");
		endUserMap.put(user2.getId(), user2);
	}

	@Override
	public TodoDTO findById(Long id) throws RuntimeException {
		if (endUserMap.get(id) != null) {
			return endUserMap.get(id);
		}
		throw new InvalidTodoException("invalid id : " + id);
	}

	@Override
	public List<TodoDTO> getAllTodo() throws RuntimeException {
		List<TodoDTO> list = new ArrayList<>();
		for (TodoDTO dto : endUserMap.values()) {
			list.add(dto);
		}
		return list;
	}

	@Override
	public TodoDTO create(String name) throws RuntimeException {
		TodoDTO dto = new TodoDTO();
		dto.setId(Util.getUUID());
		dto.setName(name + " " + dto.getId().toString());
		endUserMap.put(dto.getId(), dto);
		return dto;
	}

	@Override
	public TodoDTO delete(Long id) throws RuntimeException {
		TodoDTO dto = endUserMap.get(id);
		if (dto != null) {
			endUserMap.remove(id);
			return dto;
		}
		throw new InvalidTodoException("invalid id : " + id);
	}
}
