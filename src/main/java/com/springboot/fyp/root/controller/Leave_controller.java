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

import com.springboot.fyp.root.models.Leave;
import com.springboot.fyp.root.service.Leave_service;

@RestController
public class Leave_controller {

	@Autowired
	Leave_service leave_service;
	
	@PostMapping("/addLeaveRequest")
	public ResponseEntity<String> addLeaveRequest(@RequestBody Leave leave){
		String response = leave_service.add(leave);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getLeaveRequests/{institute_id}")
	public ResponseEntity<List<Leave>> getAllRequests(@PathVariable("institute_id") int institute_id){
		List<Leave> leaveRequests = leave_service.getAll(institute_id);
		if(leaveRequests == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(leaveRequests);
	}
	
	@DeleteMapping("/deleteLeaveRequest/{leaveId}")
	public ResponseEntity<String> deleteLeaveRequest(@PathVariable("leaveId") int leaveId){
		String response = leave_service.delete(leaveId);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(response);
	}
	
}
