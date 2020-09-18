package com.mderyol.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	public String welcome() {
		return "Mustafa";
	}
}
