package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Object_request_repository;
import com.springboot.fyp.root.models.Non_Living_Resources;
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
		
		List<Object_Request> objectRequests = getAll();
		if(objectRequests.size() != 0) {
			Object_Request request = objectRequests.get(objectRequests.size()-1);
			object_Request.setAvailableQuantity(object_Request.getQuantity()-request.getAvailableQuantity());
		}else {
			for(Non_Living_Resources resources : non_living_resource_service.getAll()) {
				if(resources.getResource_type_id() == object_Request.getResource_type_id()) {
					object_Request.setAvailableQuantity(resources.getQuantity()-object_Request.getQuantity());	
				}
				break;
			}
		}
		
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
