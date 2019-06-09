package com.joe.joeorganizer.exceptions;

import java.util.ArrayList;

import com.joe.joeorganizer.task.TaskNotfoundException;
import com.joe.joeorganizer.task.UnauthorizedTaskActionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.joe.joeorganizer.users.UserAlreadyRegisteredException;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserAlreadyRegisteredException.class)
	public ResponseEntity<?> userAlreadyRegistered(UserAlreadyRegisteredException ex, WebRequest request) {
		return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> argumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
		ArrayList<ErrorMessage> listOfMessages = new ArrayList<ErrorMessage>();
		for (ObjectError errorMessage : ex.getBindingResult().getAllErrors()) {
			listOfMessages.add(new ErrorMessage(errorMessage.getDefaultMessage()));
		}
		return new ResponseEntity<>(listOfMessages, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> badCredentials(BadCredentialsException ex,WebRequest request){
		return new ResponseEntity<>(new ErrorMessage("Password or Email is incorrect"),HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UnauthorizedTaskActionException.class)
	public ResponseEntity<?> unauthorizedTaskAction(UnauthorizedTaskActionException ex, WebRequest request){
		return new ResponseEntity<>(new ErrorMessage("unauthorized"),HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(TaskNotfoundException.class)
	public ResponseEntity<?> notfoundTask(TaskNotfoundException ex, WebRequest request){
		return new ResponseEntity<>(new ErrorMessage("this task doesn't exist"),HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> notFound(EntityNotFoundException ex, WebRequest request){
		return new ResponseEntity<>(new ErrorMessage(""),HttpStatus.NOT_FOUND);
	}


}
