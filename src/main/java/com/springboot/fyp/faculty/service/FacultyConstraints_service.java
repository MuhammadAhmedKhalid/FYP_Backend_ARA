package com.springboot.fyp.faculty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.faculty.dao.FacultyConstraints_repository;
import com.springboot.fyp.root.service.RedisUtilityRoot;
import com.springboot.fyp.root.service.SequenceGeneratorService;

@Service
public class FacultyConstraints_service {
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	@Autowired
	FacultyConstraints_repository facultyConstraints_repository;
	
	public static final String HASH_KEY_FACULTY_CONSTRAINTS_LIST = "FacultyConstraintsList";

}
