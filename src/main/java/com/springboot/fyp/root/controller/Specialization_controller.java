package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Specialization;
import com.springboot.fyp.root.service.Specialization_service;

@RestController
public class Specialization_controller {
	
	@Autowired
	Specialization_service specialization_service;
	
	@PostMapping("/addSpecialization")
	public ResponseEntity<String> addSpecialization(@RequestBody Specialization specialization){
		String response = specialization_service.add(specialization);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getSpecializations")
	public ResponseEntity<List<Specialization>> getSpecializations(){
		List<Specialization> specializations = specialization_service.getAll();
		if(specializations == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(specializations);
	}
	
}
