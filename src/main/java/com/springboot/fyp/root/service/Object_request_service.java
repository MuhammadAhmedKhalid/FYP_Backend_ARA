package com.springboot.fyp.root.service;

import java.util.ArrayList;
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
		redisUtilityRoot.deleteList(HASH_KEY_OBJECT_REQUESTS+object_Request.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Object_Request> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_OBJECT_REQUESTS+institute_id).size()>0) {
			return redisUtilityRoot.getList(HASH_KEY_OBJECT_REQUESTS+institute_id);
		}else {
			List<Object_Request> objectRequests = new ArrayList<>();
			for(Object_Request requests : object_request_repository.findAll()) {
				if(requests.getInstitute_id() == institute_id) {
					objectRequests.add(requests);
				}
			}
			
			redisUtilityRoot.saveList(objectRequests, HASH_KEY_OBJECT_REQUESTS+institute_id);
			return objectRequests;
		}
	}
	
	public String delete(int obj_req_id) {
		List<Object_Request> objectRequests = object_request_repository.findAll();
		int institute_id = 0;
		for(Object_Request requests : objectRequests) {
			if(requests.getObj_req_id() == obj_req_id) {
				institute_id = requests.getInstitute_id();
				object_request_repository.deleteById(obj_req_id);
				redisUtilityRoot.deleteList(HASH_KEY_OBJECT_REQUESTS+institute_id);
				redisUtilityRoot.saveList(object_request_repository.findAll(), HASH_KEY_OBJECT_REQUESTS+institute_id);
				return "Requested object deleted.";
			}
		}
		return null;
	}
	
}
