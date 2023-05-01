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

import com.springboot.fyp.root.models.Notification;
import com.springboot.fyp.root.service.Notification_service;

@RestController
public class Notification_controller {
	
	@Autowired
	Notification_service notification_service;
	
	@PostMapping("/addNotification")
	public ResponseEntity<String> addNotification(@RequestBody List<Notification> notifications){
		String response = notification_service.insert(notifications);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getNotifications/{institute_id}")
	public ResponseEntity<List<Notification>> getNotifications(@PathVariable("institute_id") int institute_id){
		List<Notification> notifications = notification_service.getAll(institute_id);
		if(notifications == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(notifications);
	}
	
}
