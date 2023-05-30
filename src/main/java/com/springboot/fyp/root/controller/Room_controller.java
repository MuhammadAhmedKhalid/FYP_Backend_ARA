package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		if(response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room already exists with this name in same department.");
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/rooms/{institute_id}")
	public ResponseEntity<List<Room>> getRooms(@PathVariable("institute_id") int institute_id){
		List<Room> rooms_lst = room_service.getAll(institute_id);
		if(rooms_lst == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(rooms_lst);
	}
	
	@PutMapping("/updateRoom/{room_id}/{department_id}")
	public ResponseEntity<String> updateRoom(@PathVariable("room_id") int room_id, 
			@PathVariable("department_id") int department_id, @RequestBody Room room){
		String response = room_service.update(room_id, room, department_id);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room already exists with this name in same department.");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/deleteRoom/{room_id}")
	public ResponseEntity<String> deleteRoom(@PathVariable("room_id") int room_id){
		String response = room_service.delete(room_id);
		return ResponseEntity.ok(response);
	}
	
}
