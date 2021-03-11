package com.sprint1.movie.booking.Ticket.booking.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.sprint1.movie.booking.Ticket.booking.entities.Ticket;

@Component
public interface TicketService {

	public Ticket addTicket(Ticket ticket);
	@Transactional
	public Ticket updateTicket(Ticket ticket);
	public Ticket cancelTicket(Ticket ticket);
	public Ticket viewTicket(int ticektId);
	public List<Ticket> viewTicketList();

}