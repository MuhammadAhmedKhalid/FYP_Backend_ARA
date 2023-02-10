package com.springboot.fyp.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Specialization_repository;

@Service
public class Specialization_service {
	
	@Autowired
	Specialization_repository specialization_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
}
