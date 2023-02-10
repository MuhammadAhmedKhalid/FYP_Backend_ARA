package com.springboot.fyp.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Position_repository;

@Service
public class Position_service {

	@Autowired
	Position_repository position_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
}
