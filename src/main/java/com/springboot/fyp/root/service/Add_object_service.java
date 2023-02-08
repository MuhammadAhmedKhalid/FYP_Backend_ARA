package com.springboot.fyp.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Non_living_resources_repository;
import com.springboot.fyp.root.dao.Resource_type_repository;
import com.springboot.fyp.root.models.Add_object;
import com.springboot.fyp.root.models.Non_Living_Resources;
import com.springboot.fyp.root.models.Resource_type;

@Service
public class Add_object_service {

	@Autowired
	Resource_type_repository resource_type_repository;
	
	@Autowired
	Non_living_resources_repository non_living_resources_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	public String insert(Add_object object) {
		
		Resource_type resourceType = new Resource_type();
		resourceType.setResource_type_id(sequenceGeneratorService.getSequenceNumber(resourceType.SEQUENCE_NAME));
		resourceType.setObject_name(object.getObject_name());
		resource_type_repository.insert(resourceType);
		
		Non_Living_Resources non_Living_Resources = new Non_Living_Resources();
		non_Living_Resources.setResource_id(sequenceGeneratorService.getSequenceNumber(non_Living_Resources.SEQUENCE_NAME));
		non_Living_Resources.setResource_type_id(resourceType.getResource_type_id());
		non_Living_Resources.setQuantity(object.getQuantity());
		non_Living_Resources.setRoom_id(object.getRoom_id());
		non_living_resources_repository.insert(non_Living_Resources);
		
		return "Operation performed successfully.";
	}
	
}
