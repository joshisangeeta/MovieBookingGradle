package com.sprint1.movie.booking.Ticket.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CustomerNotExistsException extends RuntimeException{

	public CustomerNotExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerNotExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
