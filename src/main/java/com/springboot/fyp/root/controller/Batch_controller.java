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

import com.springboot.fyp.root.models.Batch;
import com.springboot.fyp.root.service.Batch_service;

@RestController
public class Batch_controller {
	
	@Autowired
	Batch_service batch_service;
	
	@PostMapping("/addBatch")
	public ResponseEntity<String> addBatch(@RequestBody Batch batch){
		String response = batch_service.insert(batch);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Batch already exists with this year.");
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/batches/{institute_id}")
	public ResponseEntity<List<Batch>> getBatches(@PathVariable("institute_id") int institute_id){
		List<Batch> batchesList = batch_service.getAll(institute_id);
		if(batchesList == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(batchesList);
	}
	
	@PutMapping("/updateBatch/{batchId}/{department_id}")
	public ResponseEntity<String> updateBatch(@RequestBody int batchYear, 
			@PathVariable("batchId") int batchId, @PathVariable("department_id") int department_id){
		String response = batch_service.update(batchId, department_id, batchYear);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Batch already exists with this year.");
		}
		return ResponseEntity.ok(response);
	}
	
}
