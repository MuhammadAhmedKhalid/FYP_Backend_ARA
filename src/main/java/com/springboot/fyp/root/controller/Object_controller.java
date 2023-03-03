package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Object;
import com.springboot.fyp.root.service.Object_service;

@RestController
public class Object_controller {
	
	@Autowired
	Object_service object_service;
	
	@PostMapping("/addObject")
	public ResponseEntity<String> addObject(@RequestBody Object object){
		String response = object_service.insert(object);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/objects")
	public ResponseEntity<List<String>> getObjects(){
		return ResponseEntity.ok(object_service.getAll());
	}
	
}
