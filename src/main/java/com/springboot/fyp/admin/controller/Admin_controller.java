package com.springboot.fyp.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.admin.service.Admin_service;
import com.springboot.fyp.root.models.User;

@RestController
public class Admin_controller {

	@Autowired
	Admin_service admin_service;
	
	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody User user){
		if (admin_service.create(user) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists with this email.");
		}
		return ResponseEntity.ok(admin_service.create(user));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/login-admin")
	public ResponseEntity loginAdmin(@RequestBody User user){
		if(admin_service.signin(user.getEmail(), user.getPassword()).equals(null)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials.");
		}
		return ResponseEntity.ok(admin_service.signin(user.getEmail(), user.getPassword()));
	}
}
