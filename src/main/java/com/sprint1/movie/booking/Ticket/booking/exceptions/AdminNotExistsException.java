package com.sprint1.movie.booking.Ticket.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AdminNotExistsException extends RuntimeException{

	public AdminNotExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminNotExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
