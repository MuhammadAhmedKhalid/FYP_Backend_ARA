package com.springboot.fyp.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.MakeResBusy;
import com.springboot.fyp.root.service.MakeResBusy_service;

@RestController
public class MakeResBusy_controller {

	@Autowired
	MakeResBusy_service makeResBusy_service;
	
	@PostMapping("/makeResBusy")
	public ResponseEntity<String> makeResBusy(@RequestBody MakeResBusy makeResBusy){
		String response = makeResBusy_service.add(makeResBusy);
		return ResponseEntity.ok(response);
	}
	
}
