package com.springboot.fyp.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.User;

import com.springboot.fyp.root.service.User_service;

@RestController
public class User_controller {
	
	@Autowired
	User_service user_service;
	
	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody User user){	
		return user_service.create(user);
	}
	
}
