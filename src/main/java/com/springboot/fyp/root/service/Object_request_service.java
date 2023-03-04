package com.springboot.fyp.root.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Object_request_repository;
import com.springboot.fyp.root.models.Conflict;
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
	
	@Autowired
	Conflict_service conflict_service;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_OBJECT_REQUESTS = "ObjectRequests";

	public String add(Object_Request object_Request) throws ParseException {
		
		List<Non_Living_Resources> resources = new ArrayList<>();
		for (Non_Living_Resources resource : non_living_resource_service.getAll()) {
			if(resource.getResource_type_id() == object_Request.getResource_type_id()) {
				resources.add(resource);
			}
		}
		
		List<Object_Request> requests = new ArrayList<>();
		for(Object_Request request : object_request_repository.findAll()) {
			for(Non_Living_Resources resource : resources) {
				if(request.getResource_id() == resource.getResource_id()) {
					
					SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
					
					Date startTime1 = sf.parse(request.getStartTime());
					Date endTime1 = sf.parse(request.getEndTime());
					Date startTime2 = sf.parse(object_Request.getStartTime());
					Date endTime2 = sf.parse(object_Request.getEndTime());
					
					Conflict conflict1 = new Conflict();
					conflict1.setStartTime(startTime1);
					conflict1.setEndTime(endTime1);
					
					
					Conflict conflict2 = new Conflict();
					conflict2.setStartTime(startTime2);
					conflict2.setEndTime(endTime2);
					
					boolean conflict = conflict_service.hasConflict(conflict1, conflict2);
					
					if(conflict) {
						requests.add(request);
					}
				}
			}
		}
		
		for(Non_Living_Resources resource : resources) {
			for(Object_Request request : requests) {
				if(request.getResource_id() == resource.getResource_id()) {
					resource.setQuantity(resource.getQuantity() - request.getQuantity());
				}
			}
		}
		
		int requestedQuantity = object_Request.getQuantity();
		
		for(Non_Living_Resources resource : resources) {
			if(requestedQuantity != 0) {
				object_Request.setObj_req_id(sequenceGeneratorService.getSequenceNumber(object_Request.SEQUENCE_NAME));
				
				if(requestedQuantity <= resource.getQuantity()) {
					object_Request.setQuantity(requestedQuantity);
					requestedQuantity -= requestedQuantity;
				} else {
					object_Request.setQuantity(resource.getQuantity());
					requestedQuantity -= resource.getQuantity();
				}
				
				object_Request.setResource_id(resource.getResource_id());
				object_request_repository.insert(object_Request);
				redisUtilityRoot.deleteList(HASH_KEY_OBJECT_REQUESTS+object_Request.getInstitute_id());
			}
		}
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
