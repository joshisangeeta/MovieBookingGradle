package com.sprint1.movie.booking.Ticket.booking.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.movie.booking.Ticket.booking.entities.Ticket;
import com.sprint1.movie.booking.Ticket.booking.exceptions.TicketAlreadyExistsException;
import com.sprint1.movie.booking.Ticket.booking.exceptions.TicketNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.repository.TicketRepository;
import com.sprint1.movie.booking.Ticket.booking.servicesImplementation.TicketServiceImplementation;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketServiceImplementation ticketServiceImplementation; 
	
	@PostMapping("/")
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket){
		ResponseEntity<Ticket> re;
		try {
			ticketServiceImplementation.addTicket(ticket);
		re = new ResponseEntity<>(ticket,HttpStatus.CREATED);
		return re;
		}
		catch(Exception e){
			re = new ResponseEntity<>(ticket,HttpStatus.CONFLICT);
			return re;
		}
		
	}
	
	@RequestMapping(value = "/tickets", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateTicket(@RequestBody Ticket ticket){
		ResponseEntity<Void> re;
		
			ticketServiceImplementation.updateTicket(ticket);
			re =  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return re;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<Void> cancelTicket(@RequestBody Ticket ticket){
		ResponseEntity<Void> re;
		
			ticketServiceImplementation.cancelTicket(ticket);
			re =  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return re;
	}
	
	@GetMapping("/{ticketId}")
	public ResponseEntity<Ticket>  findByticketId(@PathVariable("ticketId") int ticketId){
		ResponseEntity<Ticket> re;
		
			Ticket ticket=ticketServiceImplementation.viewTicket(ticketId);
			re =  new ResponseEntity<Ticket>(ticket,HttpStatus.OK);
		
		return re;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Ticket>> findAllTickets(){
		ResponseEntity<List<Ticket>> re;
		List<Ticket> tickets = ticketServiceImplementation.viewTicketList();
		
			re =  new ResponseEntity<>(tickets,HttpStatus.OK);
		
		return re;
	}

}