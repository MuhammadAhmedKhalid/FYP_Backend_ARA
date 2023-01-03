package com.springboot.fyp.root.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Room;
import com.springboot.fyp.root.service.Room_service;

@RestController
public class Room_controller {

	@Autowired
	Room_service room_service;
	
	@PostMapping("/add_room")
	public ResponseEntity<String> addRoom(){
		
		List<Room> roomList = new ArrayList<>();
		roomList.add(new Room(1, "Room-01", 1));
		roomList.add(new Room(2, "Room-02", 1));
		roomList.add(new Room(3, "Room-03", 1));
		roomList.add(new Room(4, "Room-04", 1));
		roomList.add(new Room(5, "Room-05", 1));
		
		return ResponseEntity.ok(room_service.insert(roomList));
	}
	
	@GetMapping("/rooms")
	public ResponseEntity<List<Room>> getRooms(){
		return ResponseEntity.ok(room_service.getAll());
	}
	
}