package com.springboot.fyp.root.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.AddInstituteResponse;
import com.springboot.fyp.root.models.Institute;
import com.springboot.fyp.root.service.Institute_service;

@RestController
public class Institute_controller {
	
	@Autowired
	Institute_service institute_service;
	
	@PostMapping("/add_institute")
	public ResponseEntity<AddInstituteResponse> addInstitute(@RequestBody Institute institute) {
		return ResponseEntity.ok(institute_service.insert(institute));
		
	}
	
	@GetMapping("/get_institutes")
	public ResponseEntity<List<Institute>> getInstitutes(){
		List<Institute> instituteList = institute_service.getAll();
		if(instituteList.isEmpty()) {
			return ResponseEntity.ok(new ArrayList<>());
		}
		return ResponseEntity.ok(instituteList);
	}
	
	@PutMapping("updateInstitute/{institute_id}")
	public ResponseEntity<String> updateInstitute(@RequestBody Institute institute, 
			@PathVariable("institute_id") int institute_id){
		String response = institute_service.update(institute_id, institute);
		return ResponseEntity.ok(response);
	}
	
}
