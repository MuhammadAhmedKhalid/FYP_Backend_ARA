package com.springboot.fyp.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.admin.service.Admin_service;
import com.springboot.fyp.root.models.User;

@RestController
public class Admin_controller {

	@Autowired
	Admin_service admin_service;
	
	@CrossOrigin(origins = "http://localhost:3000/")
	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody User user){	
		return admin_service.create(user);
	}
	
	@SuppressWarnings("rawtypes")
//	@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = {"Authorization", "Origin"})
	@PostMapping("/login-admin")
	public ResponseEntity loginAdmin(@RequestBody User user){
		return admin_service.get(user.getEmail(), user.getPassword());
	}
	
}
