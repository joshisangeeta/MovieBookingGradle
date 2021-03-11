package com.sprint1.movie.booking.Ticket.booking.servicesImplementation;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprint1.movie.booking.Ticket.booking.entities.Screen;
import com.sprint1.movie.booking.Ticket.booking.entities.Show;
import com.sprint1.movie.booking.Ticket.booking.entities.Theatre;
import com.sprint1.movie.booking.Ticket.booking.exceptions.ScreenNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.exceptions.TheatreNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.repository.ScreenRepository;
import com.sprint1.movie.booking.Ticket.booking.repository.ShowRepository;
import com.sprint1.movie.booking.Ticket.booking.repository.TheatreRepository;
import com.sprint1.movie.booking.Ticket.booking.service.ScreenService;
import com.sprint1.movie.booking.Ticket.booking.service.TheatreService;

@Service
public class ScreenServiceImplementation implements ScreenService {
	@Autowired
	ScreenRepository screenRepository;
	
	@Autowired
	ShowRepository showRepository;

	@Autowired
	TheatreRepository theatreRepository;
	
	@Autowired
	TheatreService theatreService;
	public Screen addScreen(Screen screen) {

		return screenRepository.save(screen);
	}
	
	@Transactional
	public void updateScreen(Screen screen) {
		Optional<Screen> getUpdateScreen=screenRepository.findById(screen.getScreenId());
		System.out.println(getUpdateScreen.get());
		Screen updateScreen=new Screen();
		List<Show>shows=new ArrayList<Show>();
		System.out.println(screen.getShowList());
		
		if(getUpdateScreen.isPresent()) {
		updateScreen=getUpdateScreen.get();
		System.out.println(updateScreen);
		
		if(screen.getColumns()!=0)
		{updateScreen.setColumns(screen.getColumns());}
		
		if(screen.getRows()!=0)
		{updateScreen.setRows(screen.getRows());}
		
		if(screen.getScreenName()!=null)
		{updateScreen.setScreenName(screen.getScreenName());}
		
		if(screen.getShowList()!=null)
		{	shows.addAll(screen.getShowList());
			updateScreen.setShowList(shows);
		}
		
		if(screen.getTheatreId()!=0)
		{updateScreen.setTheatreId(screen.getTheatreId());}
		
		}
		else {
			throw new ScreenNotExistsException("Screen not exist with Id: " +  screen.getScreenId());
		}
		
	}
	
	public void removeScreen(int screenId) {
		Optional<Screen> findRemoveScreen=screenRepository.findById(screenId);
		if(findRemoveScreen.isPresent()) {
		screenRepository.delete(findRemoveScreen.get());
		}
		else {
			throw new ScreenNotExistsException("Screen not exist with Id: " +  screenId);
		}
	}
	

	public Screen viewScreenById(int screenId) {
		
		Optional<Screen> findRemoveScreen=screenRepository.findById(screenId);
		if(findRemoveScreen.isPresent()) {
			return findRemoveScreen.get();
		}
		else {
			throw new ScreenNotExistsException("Screen not exist with Id: " +  screenId);
		}
	}
	public List<Screen>viewScreenListAll(){
		List<Screen>screens=screenRepository.findAll();
		return screens;
	}
	
	public List<Screen>viewScreenList(int theatreId){
		List<Screen>screens=screenRepository.findByTheatreId(theatreId);
		System.out.println(screens);
		return screens;
	}
	
	
}
