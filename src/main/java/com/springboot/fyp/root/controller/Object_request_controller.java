package com.springboot.fyp.root.controller;

import java.text.ParseException;
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

import com.springboot.fyp.root.models.Object_Request;
import com.springboot.fyp.root.service.Object_request_service;

@RestController
public class Object_request_controller {
	
	@Autowired
	Object_request_service object_request_service;
	
	@PostMapping("/addObjectRequest")
	public ResponseEntity<String> addObjectRequest(@RequestBody Object_Request object_Request) throws ParseException{
		String response = object_request_service.add(object_Request);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getObjectRequests/{institute_id}")
	public ResponseEntity<List<Object_Request>> getAllObjectRequests(@PathVariable("institute_id") int institute_id){
		List<Object_Request> objectRequests = object_request_service.getAll(institute_id);
		if(objectRequests == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(objectRequests);
	}
	
	@DeleteMapping("/deleteObjectRequest/{obj_req_id}")
	public ResponseEntity<String> deleteObjectRequest(@PathVariable("obj_req_id") int obj_req_id){
		String response = object_request_service.delete(obj_req_id);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(response);
	}
	
}