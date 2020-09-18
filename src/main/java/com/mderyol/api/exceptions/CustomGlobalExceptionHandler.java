package com.mderyol.api.exceptions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mderyol.api.dto.ErrorDTO;

@ControllerAdvice
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ErrorDTO> unknownException(Exception ex) {
		return new ResponseEntity<>(new ErrorDTO("Exception", ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<ErrorDTO> springHandleNotFound(Exception ex, WebRequest request) throws IOException {

		if (ex instanceof ResourceNotFoundException) {
			return new ResponseEntity<>(new ErrorDTO("ResourceNotFoundException", ex.getMessage()),
					HttpStatus.NOT_FOUND);
		} else if (ex instanceof InvalidTodoException) {
			return new ResponseEntity<>(new ErrorDTO("InvalidTodoException", ex.getMessage()), HttpStatus.NOT_FOUND);
		} else if (ex instanceof BadRequestException) {
			return new ResponseEntity<>(new ErrorDTO("BadRequestException", ex.getMessage()), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(new ErrorDTO("RuntimeException", ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDTO> handleAccessDeniedException(Exception ex, HttpServletRequest request) {
		return new ResponseEntity<>(new ErrorDTO("AccessDeniedException", ex.getMessage()), HttpStatus.UNAUTHORIZED);
	}
}