package com.sprint1.movie.booking.Ticket.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint1.movie.booking.Ticket.booking.entities.Screen;
@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer>{

	public List<Screen> findByTheatreId(int theatreId);
}
