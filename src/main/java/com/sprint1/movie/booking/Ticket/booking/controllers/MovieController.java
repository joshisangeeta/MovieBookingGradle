package com.sprint1.movie.booking.Ticket.booking.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.movie.booking.Ticket.booking.entities.Movie;
import com.sprint1.movie.booking.Ticket.booking.exceptions.MovieAlreadyExistsException;
import com.sprint1.movie.booking.Ticket.booking.exceptions.MovieNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.repository.MovieRepository;
import com.sprint1.movie.booking.Ticket.booking.servicesImplementation.MovieServiceImplementation;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

	@Autowired
	MovieServiceImplementation movieServiceImplementation;
	
	@Autowired
	MovieRepository movieRepository;
	
//	Adding a Movie
	@RequestMapping(value="/" , method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie)  {
		ResponseEntity<Movie>  re;
		
			movieServiceImplementation.addMovie(movie);
			 re = new ResponseEntity<>(movie,HttpStatus.CREATED);
		
		return re;	
	}
	
//	Updating a Movie using movie_id	
	@RequestMapping(value="/" , method = RequestMethod.PUT)
	public ResponseEntity<Void> updateMovie(@RequestBody Movie movie)  {
		ResponseEntity<Void>  re;
		
		movieServiceImplementation.updateMovie(movie);
			re=new ResponseEntity<>(HttpStatus.OK);
		
		return re;
	}
	
//  Removing a Movie using movie_id 
	@RequestMapping(value="/{movieId}" , method = RequestMethod.DELETE)	
	public ResponseEntity<Void> removeMovie(@PathVariable("movieId") int movieId) {
		ResponseEntity<Void>  re;
		
		movieServiceImplementation.removeMovie(movieId);
			re=new ResponseEntity<>(HttpStatus.OK);
		
		return re;
	}
	
//  Finding Movie using movie_id
	@RequestMapping(value="/{movieId}" , method = RequestMethod.GET)
	public ResponseEntity<Movie> findMovieById(@PathVariable("movieId") int movieId) {
		ResponseEntity<Movie>  re;
		Movie getMovie=movieServiceImplementation.viewMovie(movieId);
			re=new ResponseEntity<>(getMovie,HttpStatus.OK);
		
		return re;
	}
//	Displaying all Movies present in the database
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> viewMovieList(){
		ResponseEntity<List<Movie>>  re;
		
		re=new ResponseEntity<>(movieServiceImplementation.viewMovieList(),HttpStatus.OK);
		return re;
	}
	
//  finding list of Movies using theatre_id
	@RequestMapping(value="/theatre/{theatreid}" , method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> viewMovieList(@PathVariable("theatreid") int theatreid) {
		ResponseEntity<List<Movie>>  re;
		List<Movie>movies=movieServiceImplementation.viewMovieList(theatreid);
		
		re=new ResponseEntity<>(movies,HttpStatus.OK);
		return re;
	}
	
	@RequestMapping(value="/date/{date}" , method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> viewMovieList(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) {
		ResponseEntity<List<Movie>>  re;
		List<Movie>movies=movieServiceImplementation.viewMovieList(date);
		
		re=new ResponseEntity<>(movies,HttpStatus.OK);
		return re;
	}
	
	
}
