package com.springboot.fyp.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Specialization_repository;
import com.springboot.fyp.root.models.Specialization;

@Service
public class Specialization_service {
	
	@Autowired
	Specialization_repository specialization_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	public String add(Specialization specialization) {
		specialization.setSpecialization_id(sequenceGeneratorService.getSequenceNumber(specialization.SEQUENCE_NAME));
		specialization_repository.insert(specialization);
		return "Operation performed successfully.";
	}
	
	public List<Specialization> getAll(){
		List<Specialization> specializations = specialization_repository.findAll();
		if(specializations.isEmpty()) {
			return null;
		}
		return specializations;
	}
	
}
