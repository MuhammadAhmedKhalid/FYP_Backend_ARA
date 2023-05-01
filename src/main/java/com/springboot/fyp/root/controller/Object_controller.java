package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		if(response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Object already exists with this name in same room.");
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/objects/{institute_id}")
	public ResponseEntity<List<String>> getObjects(@PathVariable("institute_id") int institute_id){
		List<String> objects = object_service.getAll(institute_id);
		if(objects == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(objects);
	}
	
	@GetMapping("/objectsPerResourceType/{resourceType}")
	public ResponseEntity<Integer> objectsPerResourceType(@PathVariable("resourceType") int resourceType){
		int totalQuantity = object_service.getObjectsPerResourceType(resourceType);
		if(totalQuantity == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(totalQuantity);
	}
	
	@DeleteMapping("/deleteObject/{resource_id}")
	public ResponseEntity<String> deleteObject(@PathVariable("resource_id") int resource_id){
		String response = object_service.delete(resource_id);
		return ResponseEntity.ok(response);
	}
	
}
