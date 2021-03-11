package com.sprint1.movie.booking.Ticket.booking.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.movie.booking.Ticket.booking.entities.Admin;
import com.sprint1.movie.booking.Ticket.booking.exceptions.AdminAlreadyExistsException;
import com.sprint1.movie.booking.Ticket.booking.exceptions.AdminNotExistsException;
import com.sprint1.movie.booking.Ticket.booking.repository.AdminRepository;
import com.sprint1.movie.booking.Ticket.booking.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
		@Autowired
		AdminRepository adminRepository;
		
		@Autowired
		AdminService ad;
		
		//adding a new admin
		@PostMapping("/addadmin")
		@ResponseStatus(code = HttpStatus.CREATED)
		public ResponseEntity<Admin> addAdmin(@RequestBody Admin a) {
			ResponseEntity<Admin>re;
			
				ad.addAdmin(a);
				re=new ResponseEntity<>(a, HttpStatus.CREATED);
			
			return re;
		}
		

    //Viewing all the admins		
		@GetMapping("/")
		public ResponseEntity<List<Admin>> findAllAdmins(){
			ResponseEntity<List<Admin>>re;
			List<Admin>admins=ad.viewAllAdmin();
			
				re=new ResponseEntity<>(admins, HttpStatus.CREATED);
			
			return re;
		}
	
		//Update admin 
		
		@PutMapping("/")
		public ResponseEntity<Void> updateAdmin(@RequestBody Admin a) {
			ResponseEntity<Void>re;
			
				ad.updateAdmin(a);
				re=new ResponseEntity<>(HttpStatus.OK);
			
			return re;
		}
		
		//View admin by id
		@GetMapping("/{id}")
		@ResponseStatus(value = HttpStatus.OK)
		public ResponseEntity<Admin> viewAdminById(@PathVariable("id") int id) {
			ResponseEntity<Admin>re;
			
			Admin findAdmin=ad.viewAdminById(id);
				re=new ResponseEntity<>(findAdmin,HttpStatus.OK);
			
			return re;
		}
		
		
		
		@GetMapping("/admins/{adminName}/{adminContact}")
		public ResponseEntity<Admin> findAdminByAdminNameAndAdminContact(@PathVariable("adminName") String adminName , @PathVariable("adminContact") String adminContact){
			ResponseEntity<Admin>re;
			Admin a = ad.ByAdminNameAndAdminContact(adminName, adminContact);
			re=new ResponseEntity<>(a,HttpStatus.OK);
		 return re;
		}
		
		
		//Deleting a admin by id
		@DeleteMapping("/deleteadmin/{id}")
		public ResponseEntity<Void> deleteAdmin(@PathVariable("id") int id) {
			ResponseEntity<Void>re;
			Optional<Admin> admin=adminRepository.findById(id);
			if(admin.isPresent()) {
			 ad.deleteAdmin(id);;
				re=new ResponseEntity<>(HttpStatus.OK);
			}
			else {
				throw new AdminNotExistsException("Admin with id:"+id+" not exists");
			}
			 return re;
		}



}