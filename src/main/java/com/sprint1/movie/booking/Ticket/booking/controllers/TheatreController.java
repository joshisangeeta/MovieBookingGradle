package com.sprint1.movie.booking.Ticket.booking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.movie.booking.Ticket.booking.entities.Theatre;
import com.sprint1.movie.booking.Ticket.booking.exceptions.TheatreNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.repository.TheatreRepository;
import com.sprint1.movie.booking.Ticket.booking.servicesImplementation.TheatreServiceImplementation;

@RestController
@RequestMapping(value = "/theatre")
public class TheatreController {
	
	@Autowired
	TheatreServiceImplementation theatreServiceImplementation;
	
	@Autowired
	TheatreRepository theatreRepository;
	
	@RequestMapping(value="/" , method = RequestMethod.POST)
	public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre theatre) {
		ResponseEntity<Theatre>  re;
			Theatre getTheatre=theatreServiceImplementation.addTheatre(theatre);
			 re = new ResponseEntity<>(getTheatre,HttpStatus.CREATED);
		return re;
	}
	
	@RequestMapping(value="/" , method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Void> updateTheatre(@RequestBody Theatre theatre) {
		ResponseEntity<Void>  re;
			theatreServiceImplementation.updateTheatre(theatre);
			re=new ResponseEntity<>(HttpStatus.OK);
		return re;
	}
	
	
	@RequestMapping(value="/id/{theatreId}" , method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeTheatreById(@PathVariable("theatreId") int theatreId) {
		ResponseEntity<Void>  re;
		
		theatreServiceImplementation.removeTheatre(theatreId);
		re=new ResponseEntity<>(HttpStatus.OK);
		return re;
	}
	
	@RequestMapping(value="/{theatreId}" , method = RequestMethod.GET)
	public ResponseEntity<Theatre> viewTheatreById(@PathVariable("theatreId") int theatreId){
		ResponseEntity<Theatre>  re;
		Theatre findTheatre=theatreServiceImplementation.viewTheatreById(theatreId);
			re = new ResponseEntity<>(findTheatre,HttpStatus.OK);
		
		return re;
	}
	
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public ResponseEntity<List<Theatre>> viewTheatreListAll(){
		ResponseEntity<List<Theatre>> re;
		List<Theatre>theatres=theatreServiceImplementation.viewTheatreList();
			re = new ResponseEntity<>(theatres,HttpStatus.OK);
		return re;
	}
	
	@RequestMapping(value="/city/{city}" , method = RequestMethod.GET)
	public ResponseEntity<List<Theatre>> viewTheatreListByCity(@PathVariable("city") String city){
		ResponseEntity<List<Theatre>>  re;
		List<Theatre> findTheatre=theatreServiceImplementation.viewTheatreListByCity(city);
			re = new ResponseEntity<>(findTheatre,HttpStatus.OK);
		return re;
	}
	
}
