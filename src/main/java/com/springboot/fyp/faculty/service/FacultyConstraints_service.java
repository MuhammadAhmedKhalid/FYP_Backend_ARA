package com.springboot.fyp.faculty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.faculty.dao.FacultyConstraints_repository;
import com.springboot.fyp.faculty.models.FacultyConstraints;
import com.springboot.fyp.root.service.SequenceGeneratorService;

@Service
public class FacultyConstraints_service {
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	FacultyConstraints_repository facultyConstraints_repository;
	
	public String add(FacultyConstraints facultyConstraints) {
		
		facultyConstraints.setFacultyConstraintId(
				sequenceGeneratorService.getSequenceNumber(facultyConstraints.SEQUENCE_NAME));
		facultyConstraints_repository.insert(facultyConstraints);
		
		return "Operation performed successfully.";
	}

}
