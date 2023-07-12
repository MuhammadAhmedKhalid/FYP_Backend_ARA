package com.springboot.fyp.faculty.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.faculty.dao.FacultyConstraints_repository;
import com.springboot.fyp.faculty.models.FacultyConstraints;
import com.springboot.fyp.root.service.RedisUtilityRoot;
import com.springboot.fyp.root.service.SequenceGeneratorService;

@Service
public class FacultyConstraints_service {
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	FacultyConstraints_repository facultyConstraints_repository;
	
	public static final String HASH_KEY_FACULTY_CONSTRAINTS_LIST = "FacultyConstraintsList";
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public String add(FacultyConstraints facultyConstraints) {
		
		facultyConstraints.setFacultyConstraintId(
				sequenceGeneratorService.getSequenceNumber(facultyConstraints.SEQUENCE_NAME));
		facultyConstraints_repository.insert(facultyConstraints);
		
		redisUtilityRoot.deleteList(HASH_KEY_FACULTY_CONSTRAINTS_LIST+facultyConstraints.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<FacultyConstraints> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_FACULTY_CONSTRAINTS_LIST+institute_id).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_FACULTY_CONSTRAINTS_LIST+institute_id);
		} else {
			if(facultyConstraints_repository.findAll().isEmpty()) {
				return null;
			}
			List<FacultyConstraints> constraints = new ArrayList<>();
			for(FacultyConstraints constraint : facultyConstraints_repository.findAll()) {
				if(constraint.getInstitute_id() == institute_id) {
					constraints.add(constraint);
				}
			}
			redisUtilityRoot.saveList(constraints, HASH_KEY_FACULTY_CONSTRAINTS_LIST+institute_id);
			return redisUtilityRoot.getList(HASH_KEY_FACULTY_CONSTRAINTS_LIST+institute_id);
		}
	}

}
