package com.springboot.fyp.faculty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.faculty.models.FacultyConstraints;
import com.springboot.fyp.faculty.service.FacultyConstraints_service;

@RestController
public class FacultyConstraints_controller {
	
	@Autowired
	FacultyConstraints_service facultyConstraints_service;

	@PostMapping("/addFacultyConstraints")
	public ResponseEntity<String> addFacultyConstraints(@RequestBody FacultyConstraints facultyConstraints){
		String response = facultyConstraints_service.add(facultyConstraints);
		return ResponseEntity.ok(response);
	}
	
}
