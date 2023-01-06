package com.springboot.fyp.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Object_request_repository;
import com.springboot.fyp.root.models.Object_Request;

@Service
public class Object_request_service {

	@Autowired
	Object_request_repository object_request_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	Non_living_resource_service non_living_resource_service;
	
	public String add(Object_Request object_Request) {
		object_Request.setObj_req_id(sequenceGeneratorService.getSequenceNumber(object_Request.SEQUENCE_NAME));
		
		int available_quantity = non_living_resource_service.getQuantity(object_Request.getResource_type_id());
		
		if(object_Request.getQuantity() > available_quantity) {
			return null;
		}
		else {
			int quantity = available_quantity - object_Request.getQuantity();
			non_living_resource_service.updateQuantity(object_Request.getResource_type_id(), quantity);
		}
		object_request_repository.insert(object_Request);
		return "Operation performed successfully.";
	}
	
}
