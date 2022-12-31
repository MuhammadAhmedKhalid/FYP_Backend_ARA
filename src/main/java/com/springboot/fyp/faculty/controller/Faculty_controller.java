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

import com.springboot.fyp.faculty.models.Faculty;
import com.springboot.fyp.faculty.service.Faculty_service;

@RestController
public class Faculty_controller {
	
	@Autowired
	Faculty_service faculty_service;
	
	@PostMapping("/create-faculty")
	public ResponseEntity<String> createFaculty(@RequestBody Faculty faculty){
		if(faculty_service.create(faculty) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faculty already exists with this email.");
		}
		return ResponseEntity.ok(faculty_service.create(faculty));
	}
	
	@GetMapping("/get-faculty/{institute_id}")
	public ResponseEntity<List<Faculty>> getFaculty(@PathVariable("institute_id") int institute_id){
		if(faculty_service.getAll(institute_id) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(faculty_service.getAll(institute_id));
	}	
}
