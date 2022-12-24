package com.springboot.fyp.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Institute_type;
import com.springboot.fyp.root.service.Institute_type_service;

@RestController
public class Institute_type_controller {
	
	@Autowired
	Institute_type_service institute_type_service;
	
	@PostMapping("/add_institute_type")
	public ResponseEntity<String> addInstituteType(@RequestBody Institute_type institute_type) {
		return institute_type_service.insert(institute_type);
		
	}
	
}
