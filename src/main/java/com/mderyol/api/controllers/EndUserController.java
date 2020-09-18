package com.mderyol.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mderyol.api.dto.TodoDTO;
import com.mderyol.api.intf.IController;
import com.mderyol.api.intf.ITodoService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/endusers")
public class EndUserController implements IController<TodoDTO> {

	@Autowired
	ITodoService todoService;

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<TodoDTO> getFindById(@PathVariable final Long id) {
		TodoDTO dto = todoService.findById(id);
		return new ResponseEntity<TodoDTO>(dto, HttpStatus.OK);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<List<TodoDTO>> getList() {
		return new ResponseEntity<List<TodoDTO>>(todoService.getAllTodo(), HttpStatus.OK);
	}

	@Override
	@PreAuthorize("@RoleService.hasRole(authentication.principal.email,'todo:add')")
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<TodoDTO> create(@RequestBody String name) {
		TodoDTO dto = todoService.create(name);
		return new ResponseEntity<TodoDTO>(dto, HttpStatus.OK);
	}

	@Override
	@PreAuthorize("@RoleService.hasRole(authentication.principal.email,'todo:delete')")
	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
	public ResponseEntity<TodoDTO> delete(@PathVariable final Long id) {
		TodoDTO dto = todoService.delete(id);
		return new ResponseEntity<TodoDTO>(dto, HttpStatus.OK);
	}
}
