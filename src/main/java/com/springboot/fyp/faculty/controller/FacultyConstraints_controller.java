package com.springboot.fyp.faculty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/getFacultyConstraints/{institute_id}")
	public ResponseEntity<List<FacultyConstraints>> getFacultyConstraints(@PathVariable("institute_id") int institute_id){
		List<FacultyConstraints> constraints = facultyConstraints_service.getAll(institute_id);
		if(constraints == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(constraints);
	}
	
}
