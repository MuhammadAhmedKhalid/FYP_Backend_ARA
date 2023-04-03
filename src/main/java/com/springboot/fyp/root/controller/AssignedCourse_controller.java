package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.AssignedCourse;
import com.springboot.fyp.root.service.AssignedCourse_service;

@RestController
public class AssignedCourse_controller {

	@Autowired
	AssignedCourse_service assignedCourse_service;
	
	@PostMapping("/assignCourse")
	public ResponseEntity<String> assignCourse(@RequestBody AssignedCourse assignedCourse){
		String response = assignedCourse_service.insert(assignedCourse);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/assignedCourses/{institute_id}")
	public ResponseEntity<List<AssignedCourse>> assignedCourses(@PathVariable("institute_id") int institute_id){
		List<AssignedCourse> assignedCourses = assignedCourse_service.getAll(institute_id);
		if(assignedCourses == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(assignedCourses);
	}
	
	@PutMapping("/updateAssignedCourse/{institute_id}/{assignedCourseId}/{faculty_id}")
	public ResponseEntity<String> updateAssignedCourse(@PathVariable("institute_id") int institute_id, 
			@PathVariable("assignedCourseId") int assignedCourseId, @PathVariable("faculty_id") int faculty_id){
		
		String response = assignedCourse_service.update(institute_id, assignedCourseId, faculty_id);
		return ResponseEntity.ok(response);
	}
	
}
