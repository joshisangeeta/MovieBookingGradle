package com.sprint1.movie.booking.Ticket.booking.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.movie.booking.Ticket.booking.entities.TicketBooking;
import com.sprint1.movie.booking.Ticket.booking.exceptions.BookingAlreadyExistsException;
import com.sprint1.movie.booking.Ticket.booking.exceptions.BookingNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.repository.TicketBookingRepository;
import com.sprint1.movie.booking.Ticket.booking.servicesImplementation.TicketBookingServiceImplementation;

@RestController
@RequestMapping(value = "Booking")
public class TicketBookingController {


	@Autowired
	TicketBookingRepository ticketBookingRepository;
	
	@Autowired
	TicketBookingServiceImplementation ticketBookingServiceImplementation; 
	
	@PostMapping("/")
	public ResponseEntity<TicketBooking> addBooking(@RequestBody TicketBooking booking){
		ResponseEntity<TicketBooking> re;
				
			
		ticketBookingServiceImplementation.addBooking(booking);

		re = new ResponseEntity<>(booking, HttpStatus.CREATED);
	
		return re;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Void> updateTicketBooking(@RequestBody TicketBooking booking){
		ResponseEntity<Void> re;
		
			ticketBookingServiceImplementation.updateBooking(booking);
			re = new ResponseEntity<>( HttpStatus.CREATED);
			
			return re;
	}
	
	@DeleteMapping("/")
	public ResponseEntity<Void> cancelBooking(@RequestBody TicketBooking ticketBooking) {
		ResponseEntity<Void> re;
		
			ticketBookingServiceImplementation.cancelBooking(ticketBooking);
			re = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			
		return re;
	}
	

	@GetMapping("/bookingsByDate/{date}")
	public ResponseEntity<List<TicketBooking>> viewBookingByDate(@PathVariable("date") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  date) {

		ResponseEntity<List<TicketBooking>> re;
		List<TicketBooking> bookings = ticketBookingServiceImplementation.showAllBookingByDate(date);
		  re =  new ResponseEntity<>(bookings,HttpStatus.OK);
		  return  re ;
	}
	
	@GetMapping("/M/{movieId}")

	public ResponseEntity<List<TicketBooking>> findByMovieId(@PathVariable ("movieId") int movieId) {
		ResponseEntity<List<TicketBooking>> re;
		List<TicketBooking>ticketBookings=ticketBookingServiceImplementation.showAllBooking(movieId);
		
			re=new ResponseEntity<List<TicketBooking>>(ticketBookings,HttpStatus.OK);
		
		return re;
	}
	
	@GetMapping("/{ticketId}")

	public ResponseEntity<List<TicketBooking>> findByTicketId(@PathVariable ("ticketId") int ticketId) {
		ResponseEntity<List<TicketBooking>> re;
		List<TicketBooking>ticketBookings=ticketBookingServiceImplementation.showAllBooking(ticketId);
		
			re=new ResponseEntity<List<TicketBooking>>(ticketBookings,HttpStatus.OK);
		
		return re;
	}
	
	@GetMapping("/S/{showId}")

	public ResponseEntity<List<TicketBooking>> findByShowId(@PathVariable ("showId") int showId) {
		ResponseEntity<List<TicketBooking>> re;
		List<TicketBooking>ticketBookings=ticketBookingServiceImplementation.showBookingList(showId);
		
		
			re=new ResponseEntity<List<TicketBooking>>(ticketBookings,HttpStatus.OK);
		
		return re;
	}
		
	@GetMapping("/")
	public ResponseEntity<List<TicketBooking>> viewAllBookings(){
		ResponseEntity<List<TicketBooking>> re;
		List<TicketBooking> tickets = ticketBookingServiceImplementation.showAllBookings();
		
			re = new ResponseEntity<>(tickets,HttpStatus.OK);
		
		return re;
	}
	
	@GetMapping("/total/{id}")
	public ResponseEntity<Double> findByticketId(@PathVariable("id") int bookingId) {
		ResponseEntity<Double> re = null;
		
		
			Double cost=ticketBookingServiceImplementation.calculateTotalCost(bookingId);
			re = new ResponseEntity<>(cost,HttpStatus.OK);
		
		return re;
	}
	
}
	
	