package com.sprint1.movie.booking.Ticket.booking.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.movie.booking.Ticket.booking.entities.Seat;
import com.sprint1.movie.booking.Ticket.booking.exceptions.SeatAlreadyExistsException;
import com.sprint1.movie.booking.Ticket.booking.exceptions.SeatNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.repository.SeatRepository;
import com.sprint1.movie.booking.Ticket.booking.servicesImplementation.SeatServiceImplementation;


@RestController
@RequestMapping(value = "/seat")
public class SeatController {
	
	@Autowired
	SeatRepository seatRepository;
	
	@Autowired
	SeatServiceImplementation seatServiceImplementation;
	
	@PostMapping("/")
	public ResponseEntity<Seat> addSeat(@RequestBody Seat seat){
		ResponseEntity<Seat> re;
		
		Seat getSeat=seatServiceImplementation.addSeat(seat);
		re = new ResponseEntity<>(getSeat,HttpStatus.CREATED);
		
		return re;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateSeat(@RequestBody Seat seat){
		ResponseEntity<Void> re;
		
			seatServiceImplementation.updateSeat(seat);
			re = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return re;
	}
	
//	@DeleteMapping("/{id}")
//	public void deleteSeat(@PathVariable("id") int id) {
//		Optional<Seat> seat = seatRepository.findById(id);
//		Seat s = null;
//		if(seat.isPresent()) {
//			s = seat.get();
//			seatRepository.delete(s);
//			System.out.println("Seat deleted");
//		}
//	}
	
	@GetMapping("/{seatId}")
	public ResponseEntity<Seat> findByticketId(@PathVariable int seatId){
		ResponseEntity<Seat> re;
		
			Seat seat=seatServiceImplementation.viewSeat(seatId);
			re = new ResponseEntity<Seat>(seat,HttpStatus.OK);
	
		return re;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Seat>> viewAllSeats(){
		ResponseEntity<List<Seat>> re;
		List<Seat>seats = seatServiceImplementation.viewSeatList();
		
			re = new ResponseEntity<>(seats,HttpStatus.OK);
		
		return re;
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSeat(@PathVariable("id") int seatId) {
		ResponseEntity<Void> re;
		
			seatServiceImplementation.removeSeat(seatId);
			re = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return re;
	}
	
	@DeleteMapping("/s/")
	public ResponseEntity<Void> deleteSeat(@RequestBody Seat seat) {
		ResponseEntity<Void> re;
		
			seatServiceImplementation.removeSeat(seat.getSeatId());
			re = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return re;
	}
	
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Seat>> viewAllSeatsByType(@PathVariable("type") String type){
		ResponseEntity<List<Seat>> re;
		List<Seat>seats=seatServiceImplementation.viewSeatByType(type);
		
	
			re = new ResponseEntity<>(seats,HttpStatus.OK);
		
		return re;
	}	
	
	
	
}