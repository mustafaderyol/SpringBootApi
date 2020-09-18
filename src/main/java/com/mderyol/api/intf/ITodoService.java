package com.mderyol.api.intf;

import java.util.List;

import com.mderyol.api.dto.TodoDTO;

public interface ITodoService {

	TodoDTO findById(Long id);

	List<TodoDTO> getAllTodo();

	TodoDTO create(String name);

	TodoDTO delete(Long id);

}
