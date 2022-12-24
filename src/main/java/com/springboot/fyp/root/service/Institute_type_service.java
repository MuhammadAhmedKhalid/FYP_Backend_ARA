package com.springboot.fyp.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Institute_type_repository;
import com.springboot.fyp.root.models.*;

@Service
public class Institute_type_service {
	
	@Autowired
	Institute_type_repository institute_type_repository;
	
	public ResponseEntity<String> insert(Institute_type institute_type){
		institute_type_repository.insert(institute_type);
		return ResponseEntity.ok("Operation performed successfully.");
	}
	
}
