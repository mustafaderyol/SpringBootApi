package com.mderyol.api.intf;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IController<T> {
	ResponseEntity<T> getFindById(Long id);

	ResponseEntity<List<T>> getList();

	ResponseEntity<T> create(String name);

	ResponseEntity<T> delete(Long id);
}
