package com.sprint1.movie.booking.Ticket.booking.servicesImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprint1.movie.booking.Ticket.booking.entities.Seat;
import com.sprint1.movie.booking.Ticket.booking.entities.Show;
import com.sprint1.movie.booking.Ticket.booking.entities.TicketBooking;
import com.sprint1.movie.booking.Ticket.booking.exceptions.BookingNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.repository.SeatRepository;
import com.sprint1.movie.booking.Ticket.booking.repository.TicketBookingRepository;
import com.sprint1.movie.booking.Ticket.booking.repository.TicketRepository;
import com.sprint1.movie.booking.Ticket.booking.service.TicketBookingService;

@Service
public class TicketBookingServiceImplementation implements TicketBookingService{
	@Autowired
	TicketBookingRepository ticketBookingRepository;

	@Autowired
	SeatRepository seatRepository;

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	ShowServiceImplementation showServiceImplementation;
	
	
public TicketBooking addBooking(TicketBooking ticketBooking) {
	
//		ticketBookingRepository.save(ticketBooking);
//		return ticketBooking;
	 	ticketBooking.setBookingDate(LocalDate.now());
		
			ticketBooking.setTransactionStatus("Confirmed");
			ticketBooking.setTransactionMode("Online");
			List<Seat>seats = ticketBooking.getTicket().getSeats();
			double cost =0;
			for(Seat s: seats) {
				cost = cost + s.getPrice();
			}
			ticketBooking.setTotalCost(cost);
			return ticketBookingRepository.save(ticketBooking);

	}



@Transactional
public TicketBooking updateBooking(TicketBooking booking) {
	Optional<TicketBooking> getUpdateBooking = ticketBookingRepository.findById(booking.getTicketId());
	TicketBooking updateBooking = null;
	if(getUpdateBooking.isPresent()) {
		updateBooking = getUpdateBooking.get();
		if(booking.getTicket()!=null) {
			updateBooking.setTicket(booking.getTicket());
		}
		if(booking.getBookingDate()!=null) {
			updateBooking.setBookingDate(booking.getBookingDate());
		}
		if(booking.getShowId()!= 0) {
			updateBooking.setShowId(booking.getShowId());
		}
		if(booking.getTotalCost()!=0) {
			updateBooking.setTotalCost(booking.getTotalCost());
		}
		if(booking.getTransactionId()!=0) {
			updateBooking.setTransactionId(booking.getTransactionId());
		}
		if(booking.getTransactionMode()!=null) {
			updateBooking.setTransactionMode(booking.getTransactionMode());
		}
		if(booking.getTransactionStatus()!=null) {
			updateBooking.setTransactionStatus(booking.getTransactionStatus());
		}
	}
	else {
		throw new BookingNotExistsException("Booking Not Exist with ID : " + booking.getTicketId());
	}
	return updateBooking;
}


@Override
public TicketBooking cancelBooking(TicketBooking ticketBooking) {
	Optional<TicketBooking> findCancelBooking = ticketBookingRepository.findById(ticketBooking.getTicketId());
	TicketBooking cancelBooking = null;
	if(findCancelBooking.isPresent()) {
		cancelBooking = findCancelBooking.get();
		ticketBookingRepository.delete(cancelBooking);
	}
	else {
		throw new BookingNotExistsException("Booking Not Exist with ID : " + ticketBooking.getTicketId());
	}
	return cancelBooking;
}


@Override
public List<TicketBooking> showAllBooking(int movieId) {
	List<TicketBooking> allBookings=new ArrayList<TicketBooking>();
	List<Show>shows=showServiceImplementation.viewAllShows();
	for(Show s:shows) {
		if(s.getMovie().getMovieId()==movieId) {
			allBookings.addAll(ticketBookingRepository.findByShowId(s.getShowId()));
		}
	}
	return allBookings;
}



public List<TicketBooking> showAllBookingByDate(LocalDate date) {
	List<TicketBooking> allBookings = ticketBookingRepository.findByBookingDate(date);
	return allBookings;
}

public List<TicketBooking> showAllBookings() {
	List<TicketBooking> allBookings = ticketBookingRepository.findAll();
	return allBookings;
}

@Override
public List<TicketBooking> showBookingList(int showId) {
	List<TicketBooking> allBookings = ticketBookingRepository.findByShowId(showId);
	return allBookings;
}


@Override
public double calculateTotalCost(int bookingid) {
	Optional<TicketBooking> findTicketBooking = ticketBookingRepository.findById(bookingid);
	
	if(findTicketBooking.isPresent()) {
	return findTicketBooking.get().getTotalCost();
	}
	else {
		throw new BookingNotExistsException("Booking Not Exist with ID : " + bookingid);
	}
}


public TicketBooking showAllBookingList(int ticketId) {
	TicketBooking allBookings = ticketBookingRepository.findByTicketId(ticketId);
	return allBookings;
}

}
