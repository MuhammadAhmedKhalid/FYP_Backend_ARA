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

import com.springboot.fyp.root.models.AllocateFaculty;
import com.springboot.fyp.root.service.AllocateFaculty_service;

@RestController
public class AllocateFaculty_controller {
	
	@Autowired
	AllocateFaculty_service allocateFaculty_service;
	
	@PostMapping("/allocate")
	public ResponseEntity<String> allocate(@RequestBody AllocateFaculty allocateFaculty){
		String res = allocateFaculty_service.add(allocateFaculty);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/allocatedFaculty/{institute_id}")
	public ResponseEntity<List<AllocateFaculty>> allocatedFaculty(@PathVariable("institute_id") int institute_id){
		List<AllocateFaculty> allocated = allocateFaculty_service.getAll(institute_id);
		if(allocated == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			return ResponseEntity.ok(allocated);
		}
	}
	
	@DeleteMapping("/deleteAllocatedFaculty/{allocateFacultyId}")
	public ResponseEntity<String> deleteAllocatedFaculty(@PathVariable("allocateFacultyId") int allocateFacultyId){
		String res = allocateFaculty_service.delete(allocateFacultyId);
		return ResponseEntity.ok(res);
	}
	
}
