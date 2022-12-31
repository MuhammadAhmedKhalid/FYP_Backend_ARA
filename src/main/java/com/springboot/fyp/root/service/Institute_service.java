package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Institute_repository;
import com.springboot.fyp.root.models.Institute;

@Service
public class Institute_service {
	
	@Autowired
	Institute_repository institute_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	public String insert(Institute institute){
		institute.setInstitute_id(sequenceGeneratorService.getSequenceNumber(institute.SEQUENCE_NAME));
		institute_repository.insert(institute);
		return institute.getInstitute_name();
	}
	
	public List<Institute> getAll(){
		if(institute_repository.findAll().isEmpty()) {
			return new ArrayList<>();
		}
		return institute_repository.findAll();
	}
}
