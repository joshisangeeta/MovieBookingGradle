package com.sprint1.movie.booking.Ticket.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class MovieNotExistsException extends RuntimeException{

	public MovieNotExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovieNotExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
}
