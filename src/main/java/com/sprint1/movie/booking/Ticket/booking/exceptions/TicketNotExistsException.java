package com.sprint1.movie.booking.Ticket.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class TicketNotExistsException extends RuntimeException{

	public TicketNotExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketNotExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
