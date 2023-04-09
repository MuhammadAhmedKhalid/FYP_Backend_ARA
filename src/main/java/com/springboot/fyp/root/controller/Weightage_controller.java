package com.springboot.fyp.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Weightage;
import com.springboot.fyp.root.service.Weightage_service;

@RestController
public class Weightage_controller {

	@Autowired
	Weightage_service weightage_service;
	
	@PostMapping("/addWeightage")
	public ResponseEntity<String> addWeightage(@RequestBody Weightage weightage){
		String response = weightage_service.insert(weightage);
		return ResponseEntity.ok(response);
	}
	
}
