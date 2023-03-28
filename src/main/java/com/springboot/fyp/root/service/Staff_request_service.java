package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Staff_request_repository;
import com.springboot.fyp.root.models.Staff_Request;

@Service
public class Staff_request_service {

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	Staff_request_repository staff_request_repository;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_STAFF_REQUESTS = "StaffRequests";
	
	public String add(Staff_Request staff_Request) {
		staff_Request.setStaff_req_id(sequenceGeneratorService.getSequenceNumber(staff_Request.SEQUENCE_NAME));
		staff_request_repository.insert(staff_Request);
		redisUtilityRoot.deleteList(HASH_KEY_STAFF_REQUESTS+staff_Request.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Staff_Request> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_STAFF_REQUESTS+institute_id).size()>0) {
			return redisUtilityRoot.getList(HASH_KEY_STAFF_REQUESTS+institute_id);
		}else {
			
			List<Staff_Request> staffRequests = new ArrayList<>();
			for(Staff_Request requests : staff_request_repository.findAll()) {
				if(requests.getInstitute_id() == institute_id) {
					staffRequests.add(requests);
				}
			}
			redisUtilityRoot.saveList(staffRequests, HASH_KEY_STAFF_REQUESTS+institute_id);
			return staffRequests;
		}
	}
	
	public String delete(int staff_req_id) {
		List<Staff_Request> staffRequests = staff_request_repository.findAll();
		int institute_id = 0;
		for(Staff_Request requests : staffRequests) {
			if(requests.getStaff_req_id() == staff_req_id) {
				institute_id = requests.getInstitute_id();
				staff_request_repository.deleteById(staff_req_id);
				redisUtilityRoot.deleteList(HASH_KEY_STAFF_REQUESTS+institute_id);
				redisUtilityRoot.saveList(staff_request_repository.findAll(), HASH_KEY_STAFF_REQUESTS+institute_id);
				return "Requested object deleted.";
			}
		}
		return null;
	}
	
}
