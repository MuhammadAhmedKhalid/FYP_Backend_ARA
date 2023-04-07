package com.springboot.fyp.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Notification;
import com.springboot.fyp.root.service.Notification_service;

@RestController
public class Notification_controller {
	
	@Autowired
	Notification_service notification_service;
	
	@PostMapping("/addNotification")
	public ResponseEntity<String> addNotification(@RequestBody Notification notification){
		String response = notification_service.insert(notification);
		return ResponseEntity.ok(response);
	}
	
}
