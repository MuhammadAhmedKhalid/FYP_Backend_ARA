package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Non_living_resources_repository;
import com.springboot.fyp.root.models.Non_Living_Resources;

@Service
public class Non_living_resource_service {
	
	@Autowired
	Non_living_resources_repository non_living_resources_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	public String insert(Non_Living_Resources resources){
		resources.setResource_id(sequenceGeneratorService.getSequenceNumber(resources.SEQUENCE_NAME));
		non_living_resources_repository.insert(resources);
		return "Operation performed successfully.";
	}
	
	public List<Non_Living_Resources> getAll(){
		List<Non_Living_Resources> resources = new ArrayList<>();
 		for (Non_Living_Resources resource : non_living_resources_repository.findAll()) {
			if(resource.getQuantity() != 0) {
				resources.add(resource);
			}
		}
 		return resources;
	}
	
	public int getQuantity(int resource_type_id) {
		for(Non_Living_Resources resources : getAll()) {
			if(resources.getResource_type_id() == resource_type_id) {
				return resources.getQuantity();
			}
		}
		return 0;
	}
	
}
