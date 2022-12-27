package com.springboot.fyp.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return admin_service.create(user);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/login-admin")
	public ResponseEntity loginAdmin(@RequestBody User user){
		return admin_service.signin(user.getEmail(), user.getPassword());
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/get-admin/{id}")
	public ResponseEntity getAdmin(@PathVariable("id") int id){
		return admin_service.get(id);
	}
	
}
