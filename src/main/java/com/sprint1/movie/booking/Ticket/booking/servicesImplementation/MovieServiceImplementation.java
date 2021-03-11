package com.sprint1.movie.booking.Ticket.booking.servicesImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprint1.movie.booking.Ticket.booking.entities.Movie;
import com.sprint1.movie.booking.Ticket.booking.entities.Show;
import com.sprint1.movie.booking.Ticket.booking.entities.Theatre;

import com.sprint1.movie.booking.Ticket.booking.exceptions.MovieNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.exceptions.TheatreNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.repository.MovieRepository;
import com.sprint1.movie.booking.Ticket.booking.repository.ShowRepository;
import com.sprint1.movie.booking.Ticket.booking.repository.TheatreRepository;
import com.sprint1.movie.booking.Ticket.booking.service.MovieService;

@Service
public class MovieServiceImplementation implements MovieService {
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	TheatreRepository theatreRepository;
	
	@Autowired
	ShowRepository showRepository;
	
	public Movie addMovie(Movie movie) {
		
		return 	movieRepository.save(movie);
		
	}
	
	@Transactional
	public Movie updateMovie(Movie movie) {
		Optional<Movie> getUpdateMovie=movieRepository.findById(movie.getMovieId());
		Movie updateMovie=null;
		if(getUpdateMovie.isPresent()) {
		updateMovie=getUpdateMovie.get();
		if(movie.getDescription()!=null)
		{updateMovie.setDescription(movie.getDescription());}
		if(movie.getLanguage()!=null)
		{updateMovie.setLanguage(movie.getLanguage());}
		if(movie.getMovieGenre()!=null)
		{updateMovie.setMovieGenre(movie.getMovieGenre());}
		if(movie.getMovieHours()!=null)
		{updateMovie.setMovieHours(movie.getMovieHours());}
		if(movie.getMovieName()!=null)
		{updateMovie.setMovieName(movie.getMovieName());}
		}
		else {
			throw new MovieNotExistsException("Movie Already Exist with ID : " +  movie.getMovieId());
		}
		return updateMovie;
	}
	
	public Movie removeMovie(int movieId) {
		Optional<Movie> findRemoveMovie=movieRepository.findById(movieId);
		Movie removeMovie=null;
		if(findRemoveMovie.isPresent()) {
		removeMovie=findRemoveMovie.get();
		movieRepository.delete(removeMovie);
		}
		else {
			throw new MovieNotExistsException("Movie Already Exist with ID : " +  movieId);
		}
		return removeMovie;
	}
	
	public Movie viewMovie(int movieId) {
		Optional<Movie> findRemoveMovie=movieRepository.findById(movieId);
		if(findRemoveMovie.isPresent()) {
			return findRemoveMovie.get();
		}
		else {
			throw new MovieNotExistsException("Movie Already Exist with ID : " + movieId);
		}
		
	}
	public List<Movie>viewMovieList(){
		List<Movie>movies=movieRepository.findAll();
		return movies;
	}
	@Transactional
	public List<Movie>viewMovieList(int theatreId){
		Optional<Theatre>theatres=theatreRepository.findById(theatreId);
		
		List<Movie>movies=null;
		if(theatres.isPresent())
		{	movies=theatres.get().getListOfMovies();}
		else {
			throw new TheatreNotExistsException("Movie Already Exist with ID : " + theatreId);
		}
		return movies;
	}
	public List<Movie>viewMovieList(LocalDate date){
		List<Show>shows=showRepository.findAll();
		List<Movie>movies = new ArrayList<Movie>();
		if(!shows.isEmpty()) {
		for(Show show:shows) {
			if(show.getShowStartTime().toLocalDate().isEqual(date)) {
				movies.add(show.getMovie());
			}
		}
		}
		return movies;
	}
}
