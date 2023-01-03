package com.springboot.fyp.root.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Non_Living_Resources;
import com.springboot.fyp.root.service.Non_living_resource_service;

@RestController
public class Non_living_resources_controller {
	
	@Autowired
	Non_living_resource_service non_living_resource_service;
	
	@PostMapping("/add_resources")
	public ResponseEntity<String> addNonLivingResource(){
		
		List<Non_Living_Resources> resourcesList = new ArrayList<>();
		resourcesList.add(new Non_Living_Resources(1, "Description", 10, 1, 1));
		resourcesList.add(new Non_Living_Resources(2, "Description", 10, 2, 1));
		resourcesList.add(new Non_Living_Resources(3, "Description", 10, 3, 1));
		resourcesList.add(new Non_Living_Resources(4, "Description", 10, 4, 1));
		resourcesList.add(new Non_Living_Resources(5, "Description", 10, 5, 1));
		
		return ResponseEntity.ok(non_living_resource_service.insert(resourcesList));
	}
	
	@GetMapping("/resources")
	public ResponseEntity<List<Non_Living_Resources>> getResources(){
		return ResponseEntity.ok(non_living_resource_service.getAll());
	}
	
	
}
