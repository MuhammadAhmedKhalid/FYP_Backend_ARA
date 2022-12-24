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
	
	public ResponseEntity<String> insert(Institute institute){
		
//		Optional<Institute_type> institute_type = institute_type_repository.findById(institute.getInstitute_type_id());
//		Institute_type check_institute_type = institute_type.get();
//		institute.setInstitute_type(check_institute_type);
		institute_repository.insert(institute);
		return ResponseEntity.ok("Operation performed successfully.");
	}
	
}
