package com.springboot.fyp.faculty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.faculty.dao.FacultyConstraints_repository;
import com.springboot.fyp.faculty.dao.Faculty_repositiory;
import com.springboot.fyp.faculty.models.Faculty;
import com.springboot.fyp.faculty.models.FacultyConstraints;
import com.springboot.fyp.root.service.SequenceGeneratorService;

@Service
public class FacultyConstraints_service {
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	FacultyConstraints_repository facultyConstraints_repository;
	
	@Autowired
	Faculty_repositiory faculty_repositiory;
	
	public String add(FacultyConstraints facultyConstraints) {
		
		facultyConstraints.setFacultyConstraintId(
				sequenceGeneratorService.getSequenceNumber(facultyConstraints.SEQUENCE_NAME));
		facultyConstraints_repository.insert(facultyConstraints);
		
		return "Operation performed successfully.";
	}
	
	public List<FacultyConstraints> getAll(int faculty_id){
		Optional<Faculty> faculty = faculty_repositiory.findById(faculty_id);
		if(facultyConstraints_repository.findAll().isEmpty()) {
			return null;
		}
		List<FacultyConstraints> constraints = new ArrayList<>();
		for(FacultyConstraints constraint : facultyConstraints_repository.findAll()) {
			if(constraint.getFaculty_id() == faculty.get().getFaculty_id()) {
				constraints.add(constraint);
			}
		}
		return constraints;
	}

}
