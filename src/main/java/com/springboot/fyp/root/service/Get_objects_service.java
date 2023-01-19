package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.models.Non_Living_Resources;
import com.springboot.fyp.root.models.Resource_type;

@Service
public class Get_objects_service {

	@Autowired
	Non_living_resource_service non_living_resource_service;
	
	@Autowired
	Resource_type_service resource_type_service;
	
	public List<String> getAll(){
		
		List<String> objects = new ArrayList<>();
		
		for(Non_Living_Resources resource : non_living_resource_service.getAll()) {
			for(Resource_type resource_type : resource_type_service.getAll()) {
				if(resource.getResource_type_id() == resource_type.getResource_type_id()) {
					objects.add(resource_type.getName());
				}
			}
		}
		
		return objects;
		
	}
	
}
