package com.sprint1.movie.booking.Ticket.booking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sprint1.movie.booking.Ticket.booking.entities.Admin;

@Component
public interface AdminService {

	
	@Transactional
	public void addAdmin(Admin admin);
	public List<Admin> viewAllAdmin();
	public Admin viewAdminById(int id);
	public void deleteAdmin(int id);
	public Admin updateAdmin(Admin a);
	public Admin ByAdminNameAndAdminContact(String adminName,String contact);
}