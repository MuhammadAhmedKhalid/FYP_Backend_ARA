package com.springboot.fyp.root.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Resource_type;
import com.springboot.fyp.root.service.Resource_type_service;

@RestController
public class Resource_type_controller {
	
	@Autowired
	Resource_type_service resource_type_service;
	
	@PostMapping("/addResourceType")
	public ResponseEntity<String> addResourceType(){
		
		List<Resource_type> resourceTypeList = new ArrayList<>();
		resourceTypeList.add(new Resource_type(1, "Light"));
		resourceTypeList.add(new Resource_type(2, "Projector"));
		resourceTypeList.add(new Resource_type(3, "Marker"));
		resourceTypeList.add(new Resource_type(4, "Duster"));
		resourceTypeList.add(new Resource_type(5, "Bracket fan"));
		
		return ResponseEntity.ok(resource_type_service.insert(resourceTypeList));
	}
	
	@GetMapping("/resourceTypes")
	public ResponseEntity<List<Resource_type>> getResourceTypes(){
		return ResponseEntity.ok(resource_type_service.getAll());
	}
	
}
