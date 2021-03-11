package com.sprint1.movie.booking.Ticket.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint1.movie.booking.Ticket.booking.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
