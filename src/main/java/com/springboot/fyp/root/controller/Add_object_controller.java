package com.springboot.fyp.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Add_object;
import com.springboot.fyp.root.service.Add_object_service;
import com.springboot.fyp.root.service.Non_living_resource_service;
import com.springboot.fyp.root.service.Resource_type_service;

@RestController
public class Add_object_controller {
	
	@Autowired
	Resource_type_service resource_type_service;
	
	@Autowired
	Add_object_service add_object_service;
	
	@Autowired
	Non_living_resource_service non_living_resource_service;

	@PostMapping("/addObject")
	public ResponseEntity<String> addObject(@RequestBody Add_object add_object){
		
		String response = add_object_service.insert(add_object);
		
		return ResponseEntity.ok(response);
	}
	
}
