package com.springboot.fyp.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Institute;
import com.springboot.fyp.root.service.Institute_service;

@RestController
public class Institute_controller {
	
	@Autowired
	Institute_service institute_service;
	
	@CrossOrigin(origins = "http://localhost:3000/")
	@PostMapping("/add_institute")
	public ResponseEntity<String> addInstitute(@RequestBody Institute institute) {
		return institute_service.insert(institute);
		
	}
	
}
