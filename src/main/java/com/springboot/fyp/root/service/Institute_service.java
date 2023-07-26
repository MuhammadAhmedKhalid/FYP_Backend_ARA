package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Institute_repository;
import com.springboot.fyp.root.models.AddInstituteResponse;
import com.springboot.fyp.root.models.Institute;

@Service
public class Institute_service {
	
	@Autowired
	Institute_repository institute_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	public static final String HASH_KEY_INSTITUTES_LIST = "InstitutesList";
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public AddInstituteResponse insert(Institute institute){
		institute.setInstitute_id(sequenceGeneratorService.getSequenceNumber(institute.SEQUENCE_NAME));
		institute_repository.insert(institute);
		redisUtilityRoot.deleteList(HASH_KEY_INSTITUTES_LIST);
		AddInstituteResponse response = new AddInstituteResponse();
		response.setInstitute_id(institute.getInstitute_id());
		response.setInstitute_name(institute.getInstitute_name());
		response.setSpringStartMonth(institute.getSpringStartMonth());
		response.setSpringEndMonth(institute.getSpringEndMonth());
		response.setFallStartMonth(institute.getFallStartMonth());
		response.setFallEndMonth(institute.getFallEndMonth());
		response.setInstituteStartTime(institute.getInstituteStartTime());
		response.setInstituteEndTime(institute.getInstituteEndTime());
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public List<Institute> getAll(){
		if(redisUtilityRoot.getList(HASH_KEY_INSTITUTES_LIST).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_INSTITUTES_LIST);
		}else {
			if(institute_repository.findAll().isEmpty()) {
				return new ArrayList<>();
			}
			redisUtilityRoot.saveList(institute_repository.findAll(), HASH_KEY_INSTITUTES_LIST);
			return redisUtilityRoot.getList(HASH_KEY_INSTITUTES_LIST);
		}
		
	}
	
	public String update(int institute_id, Institute institute) {
		List<Institute> institutes = institute_repository.findAll();
		for(Institute instituteDB : institutes) {
			if(instituteDB.getInstitute_id() == institute_id) {
				if(institute.getAddress().length() > 0) {
					instituteDB.setAddress(institute.getAddress());
				}
				if(institute.getBranch().length() > 0) {
					instituteDB.setBranch(institute.getBranch());
				}
				if(institute.getContact().length() > 0) {
					instituteDB.setContact(institute.getContact());
				}
				if(institute.getInstitute_name().length() > 0) {
					instituteDB.setInstitute_name(institute.getInstitute_name());
				}
				institute_repository.save(instituteDB);
				break;
			}
		}
		redisUtilityRoot.deleteList(HASH_KEY_INSTITUTES_LIST);
		return "Updated successfully.";
	}
	
}
