package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Non_Living_Resources;
import com.springboot.fyp.root.service.Non_living_resource_service;

@RestController
public class Non_living_resources_controller {
	
	@Autowired
	Non_living_resource_service non_living_resource_service;
	
	@PostMapping("/add_resources")
	public ResponseEntity<String> addNonLivingResource(@RequestBody Non_Living_Resources non_Living_Resources){
		String response = non_living_resource_service.insert(non_Living_Resources);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/resources")
	public ResponseEntity<List<Non_Living_Resources>> getResources(){
		return ResponseEntity.ok(non_living_resource_service.getAll());
	}
	
	
}
