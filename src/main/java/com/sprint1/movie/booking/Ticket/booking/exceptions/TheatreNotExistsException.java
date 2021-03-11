package com.sprint1.movie.booking.Ticket.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class TheatreNotExistsException extends RuntimeException{

	public TheatreNotExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TheatreNotExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
