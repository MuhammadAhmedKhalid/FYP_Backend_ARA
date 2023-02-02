package com.springboot.fyp.root.service;

import java.util.List;

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
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_OBJECT_REQUESTS = "ObjectRequests";
		
	public String add(Object_Request object_Request) {
		
		object_Request.setObj_req_id(sequenceGeneratorService.getSequenceNumber(object_Request.SEQUENCE_NAME));
		int available_quantity = non_living_resource_service.getQuantity(object_Request.getResource_type_id());
		if(object_Request.getQuantity() > available_quantity) {			
			return null;
		}
		
		object_request_repository.insert(object_Request);
		redisUtilityRoot.deleteList(HASH_KEY_OBJECT_REQUESTS);
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Object_Request> getAll(){
		if(redisUtilityRoot.getList(HASH_KEY_OBJECT_REQUESTS).size()>0) {
			return redisUtilityRoot.getList(HASH_KEY_OBJECT_REQUESTS);
		}else {
			List<Object_Request> objectRequests = object_request_repository.findAll();
			redisUtilityRoot.saveList(objectRequests, HASH_KEY_OBJECT_REQUESTS);
			return redisUtilityRoot.getList(HASH_KEY_OBJECT_REQUESTS);
		}
	}
	
}
