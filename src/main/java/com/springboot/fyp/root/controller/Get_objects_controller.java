package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.service.Get_objects_service;

@RestController
public class Get_objects_controller {

	@Autowired
	Get_objects_service get_objects_service;
	
	@GetMapping("/objects")
	public ResponseEntity<List<String>> getObjects(){
		return ResponseEntity.ok(get_objects_service.getAll());
	}
	
}
