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

import com.springboot.fyp.root.models.OfferCourse;
import com.springboot.fyp.root.service.OfferCourse_service;

@RestController
public class OfferCourse_controller {

	@Autowired
	OfferCourse_service offerCourse_service;
	
	@PostMapping("/offerCourse")
	public ResponseEntity<String> offerCourse(@RequestBody OfferCourse offerCourse){
		String res = offerCourse_service.add(offerCourse);
		if(res == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course already offered.");
		} else {
			return ResponseEntity.ok(res);
		}
	}
	
	@GetMapping("offeredCourses/{institute_id}")
	public ResponseEntity<List<OfferCourse>> offeredCourses(@PathVariable("institute_id") int institute_id){
		List<OfferCourse> offeredCourses = offerCourse_service.getAll(institute_id);
		if(offeredCourses == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			return ResponseEntity.ok(offeredCourses);
		}
	}
	
	@PutMapping("updateOfferedCourse/{offerCourseId}")
	public ResponseEntity<String> updateOfferedCourse(@RequestBody OfferCourse offerCourse, 
			@PathVariable("offerCourseId") int offerCourseId){
		String res = offerCourse_service.update(offerCourseId, offerCourse);
		if(res == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course already offered.");
		}
		return ResponseEntity.ok(res);
	}
	
	
	@DeleteMapping("/deleteOfferedCourse/{offerCourseId}")
	public ResponseEntity<String> deleteOfferedCourse(@PathVariable("offerCourseId") int offerCourseId){
		String res = offerCourse_service.delete(offerCourseId);
		return ResponseEntity.ok(res);
	}
	
}
