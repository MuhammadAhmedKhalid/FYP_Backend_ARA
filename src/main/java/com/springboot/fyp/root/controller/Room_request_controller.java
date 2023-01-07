package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Room_Request;
import com.springboot.fyp.root.service.Room_request_service;

@RestController
public class Room_request_controller {
	
	@Autowired
	Room_request_service room_request_service;
	
	@PostMapping("/addRoomRequest")
	public ResponseEntity<String> addRoomRequest(@RequestBody Room_Request room_Request){
		String response = room_request_service.add(room_Request);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requested room is not available.");
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getRoomRequests")
	public ResponseEntity<List<Room_Request>> getAllRequests(){
		return ResponseEntity.ok(room_request_service.getAll());
	}
	
}
