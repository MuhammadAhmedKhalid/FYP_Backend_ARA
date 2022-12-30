package com.springboot.fyp.faculty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.faculty.models.Faculty;
import com.springboot.fyp.faculty.service.Faculty_service;

@RestController
public class Faculty_controller {
	
	@Autowired
	Faculty_service faculty_service;
	
	@PostMapping("/create-faculty")
	public ResponseEntity<String> createFaculty(@RequestBody Faculty faculty){
		return faculty_service.create(faculty);
	}
	
}
