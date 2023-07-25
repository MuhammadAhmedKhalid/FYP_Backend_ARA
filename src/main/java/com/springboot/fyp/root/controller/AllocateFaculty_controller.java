package com.springboot.fyp.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.AllocateFaculty;
import com.springboot.fyp.root.service.AllocateFaculty_service;

@RestController
public class AllocateFaculty_controller {
	
	@Autowired
	AllocateFaculty_service allocateFaculty_service;
	
	@PostMapping("/allocate")
	public ResponseEntity<String> allocate(@RequestBody AllocateFaculty allocateFaculty){
		String res = allocateFaculty_service.add(allocateFaculty);
		return ResponseEntity.ok(res);
	}

}
