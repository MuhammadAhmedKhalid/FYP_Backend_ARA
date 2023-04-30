package com.springboot.fyp.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.admin.service.Admin_service;
import com.springboot.fyp.root.models.JWT_Response;
import com.springboot.fyp.root.models.User;
import com.springboot.fyp.root.service.JWT_Utils;

@RestController
public class Admin_controller {

	@Autowired
	Admin_service admin_service;
	
	@Autowired
	JWT_Utils jwt_Utils;
	
	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody User user){
		String response = admin_service.create(user, false);
		if (response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists with this email.");
		}
		return ResponseEntity.ok(response);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity loginAdmin(@RequestBody User user){
		JWT_Response response = admin_service.signin(user.getEmail(), user.getPassword());
		if(response == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials.");
		}
		return ResponseEntity.ok(response);
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/checkTokenValidity")
	public ResponseEntity checkToken(@RequestBody String token) {
		boolean response = jwt_Utils.isTokenExpired(token);
		return ResponseEntity.ok(response);
	}
	
}
