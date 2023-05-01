package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Weightage;
import com.springboot.fyp.root.models.WeightageDB;
import com.springboot.fyp.root.service.Weightage_service;

@RestController
public class Weightage_controller {

	@Autowired
	Weightage_service weightage_service;
	
	@PostMapping("/addWeightage")
	public ResponseEntity<String> addWeightage(@RequestBody Weightage weightage){
		String response = weightage_service.insert(weightage);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getWeightage/{institute_id}")
	public ResponseEntity<List<WeightageDB>> getWeightage(@PathVariable("institute_id") int institute_id){
		List<WeightageDB> weightage_lst = weightage_service.getAll(institute_id);
		if(weightage_lst == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(weightage_lst);
	}
	
	@PutMapping("/updateWeightage/{weightageId}")
	public ResponseEntity<String> updateWeightage(@PathVariable("weightageId") int weightageId){
		String response = weightage_service.update(weightageId);
		return ResponseEntity.ok(response);
	}
	
}
