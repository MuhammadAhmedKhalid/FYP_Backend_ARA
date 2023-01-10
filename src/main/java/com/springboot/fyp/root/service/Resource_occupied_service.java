package com.springboot.fyp.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Resource_occupied_repository;
import com.springboot.fyp.root.models.Resource_occupied;

@Service
public class Resource_occupied_service {
	
	@Autowired
	Resource_occupied_repository resource_occupied_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	public String add(Resource_occupied resource_occupied) {
		
		resource_occupied.setOccupied_resource_id(
				sequenceGeneratorService.getSequenceNumber(resource_occupied.SEQUENCE_NAME));
		resource_occupied_repository.insert(resource_occupied);
		
		return "Operation performed successfully.";
	}
	
}
