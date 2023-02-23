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

import com.springboot.fyp.root.models.Staff_Request;
import com.springboot.fyp.root.service.Staff_request_service;

@RestController
public class Staff_request_controller {
	
	@Autowired
	Staff_request_service staff_request_service;
	
	@PostMapping("/addStaffRequest")
	public ResponseEntity<String> addStaffRequest(@RequestBody Staff_Request staff_Request){
		String response = staff_request_service.add(staff_Request);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faculty member is not available.");
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getStaffRequests/{institute_id}")
	public ResponseEntity<List<Staff_Request>> getAllRequests(@PathVariable("institute_id") int institute_id){
		List<Staff_Request> staffRequests = staff_request_service.getAll(institute_id);
		if(staffRequests == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(staffRequests);
	}
	
}
