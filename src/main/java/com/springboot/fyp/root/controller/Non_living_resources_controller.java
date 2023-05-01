package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Non_Living_Resources;
import com.springboot.fyp.root.service.Non_living_resource_service;

@RestController
public class Non_living_resources_controller {
	
	@Autowired
	Non_living_resource_service non_living_resource_service;
	
	@GetMapping("/resources/{institute_id}")
	public ResponseEntity<List<Non_Living_Resources>> getResources(@PathVariable("institute_id") int institute_id){
		List<Non_Living_Resources> non_Living_Resources_lst = non_living_resource_service.getAll(institute_id);
		if(non_Living_Resources_lst == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(non_Living_Resources_lst);
	}

}
