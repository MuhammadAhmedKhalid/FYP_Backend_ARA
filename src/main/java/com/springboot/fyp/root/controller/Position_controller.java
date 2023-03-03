package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Position;
import com.springboot.fyp.root.service.Position_service;

@RestController
public class Position_controller {

	@Autowired
	Position_service position_service;
	
	@PostMapping("/addPosition")
	public ResponseEntity<String> addPosition(@RequestBody Position position){
		String response = position_service.add(position);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Position already exists with this name.");
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getPositions/{institute_id}")
	public ResponseEntity<List<Position>> getPositions(@PathVariable("institute_id") int institute_id){
		List<Position> positions = position_service.getAll(institute_id);
		if(positions == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(positions);
	}
	
}
