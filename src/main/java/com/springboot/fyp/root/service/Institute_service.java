package com.springboot.fyp.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Institute_repository;
import com.springboot.fyp.root.models.Institute;

@Service
public class Institute_service {
	
	@Autowired
	Institute_repository institute_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	public ResponseEntity<String> insert(Institute institute){
		institute.setInstitue_id(sequenceGeneratorService.getSequenceNumber(institute.SEQUENCE_NAME));
		institute_repository.insert(institute);
		return ResponseEntity.ok("Operation performed successfully.");
	}
	
}
