package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Object_request_repository;
import com.springboot.fyp.root.models.Object_Request;
import com.springboot.fyp.root.models.Resource_occupied;

@Service
public class Object_request_service {

	@Autowired
	Object_request_repository object_request_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	Non_living_resource_service non_living_resource_service;
	
	@Autowired
	Resource_occupied_service resource_occupied_service;
		
	public String add(Object_Request object_Request) {
		
		object_Request.setObj_req_id(sequenceGeneratorService.getSequenceNumber(object_Request.SEQUENCE_NAME));
		int available_quantity = non_living_resource_service.getQuantity(object_Request.getResource_type_id());
		if(object_Request.getQuantity() > available_quantity) {			
			return null;
		}
		
		Resource_occupied resource_occupied = new Resource_occupied(object_Request.getResource_type_id(), 
				object_Request.getRoom_id(), object_Request.getQuantity(), object_Request.getStartTime(), object_Request.getEndTime(), 
				object_Request.getDate());
		resource_occupied_service.add(resource_occupied);
		
		object_request_repository.insert(object_Request);
		return "Operation performed successfully.";
	}
	
	public List<Object_Request> getAll(){
		List<Object_Request> objectRequests = object_request_repository.findAll();
		if(objectRequests.size() != 0) {
			return objectRequests;
		}
		return new ArrayList<>();
	}
	
}
