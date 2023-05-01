package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.AssignedCoursesForTable;
import com.springboot.fyp.root.service.AssignedCoursesForTable_service;

@RestController
public class AssignedCoursesForTable_controller {
	
	@Autowired
	AssignedCoursesForTable_service assignedCoursesForTable_service;
	
	@GetMapping("/assignedCoursesForTable/{institute_id}")
	public ResponseEntity<List<AssignedCoursesForTable>> assignedCourses(@PathVariable("institute_id") int institute_id){
		List<AssignedCoursesForTable> assignedCourses = assignedCoursesForTable_service.getAll(institute_id);
		if(assignedCourses == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(assignedCourses);
	}
	
}
