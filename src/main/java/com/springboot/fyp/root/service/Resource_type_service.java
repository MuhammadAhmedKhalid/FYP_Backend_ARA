package com.springboot.fyp.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Resource_type_repository;
import com.springboot.fyp.root.models.Resource_type;

@Service
public class Resource_type_service {
	
	@Autowired
	Resource_type_repository resource_type_repository;
	
	public String insert(List<Resource_type> resourceTypeList){
		for (Resource_type resource_type : resourceTypeList) {
			resource_type_repository.insert(resource_type);
		}
		return "Operation performed successfully.";
	}
	
	public List<Resource_type> getAll(){
		return resource_type_repository.findAll();
	}
	
}	
