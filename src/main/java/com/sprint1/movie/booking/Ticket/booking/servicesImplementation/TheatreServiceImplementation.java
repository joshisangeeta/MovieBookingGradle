package com.sprint1.movie.booking.Ticket.booking.servicesImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sprint1.movie.booking.Ticket.booking.entities.Movie;
import com.sprint1.movie.booking.Ticket.booking.entities.Screen;
import com.sprint1.movie.booking.Ticket.booking.entities.Theatre;
import com.sprint1.movie.booking.Ticket.booking.exceptions.TheatreNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.repository.ShowRepository;
import com.sprint1.movie.booking.Ticket.booking.repository.TheatreRepository;
import com.sprint1.movie.booking.Ticket.booking.service.TheatreService;

@Service
public class TheatreServiceImplementation implements TheatreService{
	@Autowired
	TheatreRepository theatreRepository;
	
	@Autowired
	ShowRepository showRepository;
	
	@Autowired
	MovieServiceImplementation movieServiceImplementation;

	@Autowired
	ScreenServiceImplementation screenServiceImplementation;
	
	public Theatre addTheatre(Theatre theatre) {
				return	theatreRepository.save(theatre);
	}
	
	@Transactional()
	public void updateTheatre(Theatre theatre) {
		
		Optional<Theatre> findTheatre = theatreRepository.findById(theatre.getTheatreId());
		Theatre updateTheatre=new Theatre();
		if(findTheatre.isPresent()) {
			
			if(findTheatre.isPresent()) {
			updateTheatre=findTheatre.get();
			if(theatre.getListOfMovies()!=null && !theatre.getListOfMovies().isEmpty())
			{updateTheatre.setListOfMovies(theatre.getListOfMovies());}
			if(theatre.getListOfScreens()!=null && !theatre.getListOfScreens().isEmpty())
			{updateTheatre.setListOfScreens(theatre.getListOfScreens());}
			if(theatre.getManagerContact()!=null)
			{updateTheatre.setManagerContact(theatre.getManagerContact());}
			if(theatre.getManagerName()!=null)
			{updateTheatre.setManagerName(theatre.getManagerName());}
			if(theatre.getTheatreCity()!=null)
			{updateTheatre.setTheatreCity(theatre.getTheatreCity());}
			if(theatre.getTheatreCity()!=null)
			{updateTheatre.setTheatreName(theatre.getTheatreName());}
			}
		}
		else {
			throw new TheatreNotExistsException("Theatre not exist with Id ");
		}
	}
	
	public void removeTheatre(int theatreId) {
		Optional<Theatre> findRemoveTheatre=theatreRepository.findById(theatreId);
		
		if(findRemoveTheatre.isPresent()) {
		theatreRepository.delete(findRemoveTheatre.get());
		}
		else {
			throw new TheatreNotExistsException("Theatre not exist with Id ");
		}
	}
	
	public Theatre viewTheatreById(int theatreId) {
		Optional<Theatre> findTheatre=theatreRepository.findById(theatreId);
		if(findTheatre.isPresent()) {
			return findTheatre.get();
		}
		else {
			throw new TheatreNotExistsException("Theatre not exist with Id ");
		}
		
	}
	public List<Theatre>viewTheatreList(){
		List<Theatre>theatres=theatreRepository.findAll();
		return theatres;
	}
	
	public List<Theatre>viewTheatreListByCity(String City){
		List<Theatre>theatres=theatreRepository.findTheatreWithCity(City);
			
		return theatres;
	}
	
	

	public Theatre theatreAddMovie(int theatreId,Movie movie) {
		Optional<Theatre> findTheatre=theatreRepository.findById(theatreId);
		if(findTheatre.isPresent()) {
			Theatre theatre=findTheatre.get();
			theatre.getListOfMovies().add(movie);
			theatreRepository.save(theatre);
			return theatre;
		}
		return null;
	}
	
	public Theatre theatreRemoveMovie(int theatreId,int movieid) {
		Optional<Theatre> findTheatre=theatreRepository.findById(theatreId);
		if(findTheatre.isPresent()) {
			Theatre theatre=findTheatre.get();
			if(movieServiceImplementation.viewMovie(movieid)!=null && theatre.getListOfMovies().get(theatre.getListOfMovies().indexOf(movieServiceImplementation.viewMovie(movieid)))!=null)
			{
			theatre.getListOfMovies().remove(movieServiceImplementation.viewMovie(movieid));
			theatreRepository.save(theatre);
			return theatre;
			}
		}
		return null;
	}
	
	@Transactional
	public Theatre theatreAddScreen(int theatreId,Screen screen) {
		Optional<Theatre> findTheatre=theatreRepository.findById(theatreId);
		if(findTheatre.isPresent() && findTheatre.get().getTheatreId()==screen.getTheatreId()) {
			Theatre theatre=findTheatre.get();
			System.out.println(theatre);
			System.out.println(screen);
			Screen s=new Screen(2, null, null, 1, 3);
		
			//theatre.getListOfScreens().add(s);
			System.out.println(theatre.getListOfScreens());
			return theatreRepository.save(theatre);
			
		}else {
			throw new TheatreNotExistsException("Theatre not exist with Id ");
		}
		
	}
	
	public Theatre theatreRemoveScreen(int theatreId,int screenId) {
		Optional<Theatre> findTheatre=theatreRepository.findById(theatreId);
		if(findTheatre.isPresent()) {
			Theatre theatre=findTheatre.get();
			
			for(Screen s:theatre.getListOfScreens())
			{
				if(s.getScreenId()==screenId)
				{
					
					theatre.getListOfScreens().remove(s);
					theatreRepository.save(theatre);
					System.out.println();
					
					return theatre;
				}
			
			}
		}
		return null;
	}
}
