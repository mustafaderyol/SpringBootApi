package com.mderyol.api.exceptions;

public class InvalidTodoException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 4168807902048880830L;

	public InvalidTodoException(String message) {
		super(message);
	}
}
