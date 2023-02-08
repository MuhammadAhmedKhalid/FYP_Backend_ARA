package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Resource_type;
import com.springboot.fyp.root.service.Resource_type_service;

@RestController
public class Resource_type_controller {
	
	@Autowired
	Resource_type_service resource_type_service;
	
	@GetMapping("/resourceTypes")
	public ResponseEntity<List<Resource_type>> getResourcesTypes(){
		return ResponseEntity.ok(resource_type_service.getAll());
	}
	
}
