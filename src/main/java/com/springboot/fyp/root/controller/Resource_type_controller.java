package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Resource_type;
import com.springboot.fyp.root.service.Resource_type_service;

@RestController
public class Resource_type_controller {
	
	@Autowired
	Resource_type_service resource_type_service;
	
	@GetMapping("/resourceTypes/{institute_id}")
	public ResponseEntity<List<Resource_type>> getResourcesTypes(@PathVariable("institute_id") int institute_id){
		List<Resource_type> resource_types = resource_type_service.getAll(institute_id);
		if(resource_types == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(resource_types);
	}
	
}
