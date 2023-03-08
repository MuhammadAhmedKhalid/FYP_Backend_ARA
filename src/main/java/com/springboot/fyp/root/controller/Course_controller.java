package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Course;
import com.springboot.fyp.root.service.Course_service;

@RestController
public class Course_controller {
	
	@Autowired
	Course_service course_service;
	
	@PostMapping("/addCourse")
	public ResponseEntity<String> addCourse(@RequestBody Course course){
		String response = course_service.add(course);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course already exists with this name.");
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getCourses/{institute_id}")
	public ResponseEntity<List<Course>> getCourses(@PathVariable("institute_id") int institute_id){
		List<Course> courses = course_service.getAll(institute_id);
		if(courses == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(courses);
	}
	
}
