package com.springboot.fyp.root.service;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Institute_type_repository;
import com.springboot.fyp.root.models.*;

@Service
public class Institute_type_service {
	
	@Autowired
	Institute_type_repository institute_type_repository;
	
	public ResponseEntity<List<Institute_type>> get(){
		
		if(institute_type_repository.findAll().size() == 0) {
			HashMap<String, List<Institute_type>> instituteTypes = new HashMap<>();
			instituteTypes.put("Education", Arrays.asList(new Institute_type(1, "School"), 
					new Institute_type(2, "University"), 
					new Institute_type(3, "College")));
			instituteTypes.put("Factory", Arrays.asList(new Institute_type(4, "Food"), 
					new Institute_type(5, "Clothing and textiles"), 
					new Institute_type(6, "Metal")));
			instituteTypes.put("Hospital", Arrays.asList(new Institute_type(7, "Women's hospitals"), 
					new Institute_type(8, "Children's hospitals"), 
					new Institute_type(9, "Trauma Center Hospitals")));
			instituteTypes.put("Office", Arrays.asList(new Institute_type(10, "Private Office"), 
					new Institute_type(11, "Virtual Office"), 
					new Institute_type(12, "Coworking Desk")));
			
			for ( String key : instituteTypes.keySet() ) {
			    for(Institute_type value : instituteTypes.get(key)) {
			    	Institute_type institute_type = new Institute_type(value.getInstitute_type_id(), value.getName(), key);
			    	institute_type_repository.insert(institute_type);
			    }
			}
		}
		return ResponseEntity.ok(institute_type_repository.findAll());
	}
	
}
