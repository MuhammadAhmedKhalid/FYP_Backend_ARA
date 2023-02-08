package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Room;
import com.springboot.fyp.root.service.Room_service;

@RestController
public class Room_controller {

	@Autowired
	Room_service room_service;
	
	@PostMapping("/add_room")
	public ResponseEntity<String> addRoom(@RequestBody Room room){
		String response = room_service.insert(room);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/rooms")
	public ResponseEntity<List<Room>> getRooms(){
		return ResponseEntity.ok(room_service.getAll());
	}
	
}
