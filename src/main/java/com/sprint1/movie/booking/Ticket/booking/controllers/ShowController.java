package com.sprint1.movie.booking.Ticket.booking.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.movie.booking.Ticket.booking.entities.Screen;
import com.sprint1.movie.booking.Ticket.booking.entities.Show;
import com.sprint1.movie.booking.Ticket.booking.exceptions.ScreenNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.exceptions.ShowAlreadyExistsException;
import com.sprint1.movie.booking.Ticket.booking.exceptions.ShowNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.repository.ScreenRepository;
import com.sprint1.movie.booking.Ticket.booking.repository.ShowRepository;
import com.sprint1.movie.booking.Ticket.booking.servicesImplementation.ShowServiceImplementation;

@RestController
@RequestMapping(value = "/show")
public class ShowController {

	@Autowired
	ShowServiceImplementation showServiceImplementation;
	
	@Autowired
	ShowRepository showRepository;
	
	@Autowired
	ScreenRepository screenRepository;
	
	@RequestMapping(value="/" , method = RequestMethod.POST)
	public ResponseEntity<Show> addShow(@RequestBody Show show) {
		ResponseEntity<Show>  re;
		Show getShow=showServiceImplementation.addShow(show);
		re=new ResponseEntity<>(getShow,HttpStatus.CREATED);
		return re;
	}
	
	@RequestMapping(value="/" , method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Show> updateShow(@RequestBody Show show) {
		ResponseEntity<Show>  re;
		Optional<Show> findShow = showRepository.findById(show.getShowId());
		if(findShow.isPresent()) {
		Show getShow=showServiceImplementation.updateShow(show);
			re=new ResponseEntity<>(getShow,HttpStatus.OK);
		}
		else {
			throw new ShowNotExistsException("Show not exist with Id: " +  show.getShowId());
		}
		return re;
	}
	
	@RequestMapping(value="/" , method = RequestMethod.DELETE)
	public ResponseEntity<Show> removeShow(@RequestBody Show show) {
		Show removeShow=showServiceImplementation.removeShow(show);
		ResponseEntity<Show>  re;
		re=new ResponseEntity<>(removeShow,HttpStatus.OK);
		return re;
	}
	
	@RequestMapping(value="/{show}" , method = RequestMethod.GET)
	public ResponseEntity<Show> viewShow(@PathVariable("show") Show show){
		ResponseEntity<Show>  re;
		Show findShow=showServiceImplementation.viewShow(show);
			re = new ResponseEntity<>(findShow,HttpStatus.FOUND);
		return re;
	}
	
	@RequestMapping(value="/{showId}" , method = RequestMethod.GET)
	public ResponseEntity<Show> viewScreenById(@PathVariable("showId") int showId){
		ResponseEntity<Show>  re;
		Show findShow=showServiceImplementation.viewShowById(showId);
		
			re = new ResponseEntity<>(findShow,HttpStatus.FOUND);
		
		return re;
	}
	
	@RequestMapping(value="/theatre/{theatreId}" , method = RequestMethod.GET)
	public ResponseEntity<List<Show>> viewScreenBytheatreId(@PathVariable("theatreId") int theatreId){
		ResponseEntity<List<Show>>  re;
		List<Show> findShow=showServiceImplementation.viewShowList(theatreId);
		
			re = new ResponseEntity<>(findShow,HttpStatus.FOUND);
		
		return re;
	}
	
	@RequestMapping(value="/LocalDate/{date}" , method = RequestMethod.GET)
	public ResponseEntity<List<Show>> viewScreenByDate(@PathVariable("date") @DateTimeFormat(iso=ISO.DATE)  LocalDate date){
		ResponseEntity<List<Show>>  re;
		List<Show> findShow=showServiceImplementation.viewShowList(date);
		
			re = new ResponseEntity<>(findShow,HttpStatus.FOUND);
		
		return re;
	}
	
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public ResponseEntity<List<Show>> viewScreensAll(){
		ResponseEntity<List<Show>>  re;
		List<Show> findShow=showServiceImplementation.viewAllShows();
		
			re = new ResponseEntity<>(findShow,HttpStatus.FOUND);
		
		return re;
	}
	
	
}
