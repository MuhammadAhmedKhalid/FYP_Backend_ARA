package com.springboot.fyp.root.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Institute;
import com.springboot.fyp.root.service.Institute_service;

@RestController
public class Institute_controller {
	
	@Autowired
	Institute_service institute_service;
	
	@PostMapping("/add_institute")
	public ResponseEntity<String> addInstitute(@RequestBody Institute institute) {
		return institute_service.insert(institute);
		
	}
	
	@GetMapping("/get_institutes")
	public ResponseEntity<List<Institute>> getInstitutes(){
		if(institute_service.getAll().isEmpty()) {
			return ResponseEntity.ok(new ArrayList<>());
		}
		return ResponseEntity.ok(institute_service.getAll());
	}
	
}
