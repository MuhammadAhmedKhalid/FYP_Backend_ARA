package com.springboot.fyp.faculty.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.fyp.admin.service.Admin_service;
import com.springboot.fyp.faculty.dao.Faculty_repositiory;
import com.springboot.fyp.faculty.models.Faculty;
import com.springboot.fyp.root.dao.User_repository;
import com.springboot.fyp.root.service.SequenceGeneratorService;

@Service
public class Faculty_service {
	
	final String secretKey = "3t6w9y$B&E)H@McQ";
	
	@Autowired
	Faculty_repositiory faculty_repositiory;
	
	@Autowired
	User_repository user_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	Admin_service admin_service;
	
	public ResponseEntity<String> create(Faculty faculty){
		Faculty checkFaculty = faculty_repositiory.findByOfficialEmailAddress(faculty.getOfficialEmailAddress()); 
		if(checkFaculty != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faculty already exists with this email.");
		}
		admin_service.create(faculty.getUser());
		faculty.setFaculty_id(sequenceGeneratorService.getSequenceNumber(faculty.SEQUENCE_NAME));
		faculty_repositiory.insert(faculty);
		return ResponseEntity.ok("Operation performed successfully.");
	}

	public ResponseEntity<List<Faculty>> getAll(int institute_id) {
		if(faculty_repositiory.findAll().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		List<Faculty> faculty_list = new ArrayList<>();
		for(Faculty faculty : faculty_repositiory.findAll()) {
			if( faculty.getInstitute_id() == institute_id) {
				faculty_list.add(faculty);
			}
		}
		return ResponseEntity.ok(faculty_list);
	}
	
}
