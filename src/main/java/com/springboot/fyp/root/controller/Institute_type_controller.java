package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Institute_type;
import com.springboot.fyp.root.service.Institute_type_service;

@RestController
public class Institute_type_controller {
	
	@Autowired
	Institute_type_service institute_type_service;

	@GetMapping("/get_institute_types")
	public ResponseEntity<List<Institute_type>> getInstituteType() {
		return ResponseEntity.ok(institute_type_service.get());
		
	}
	
}
