package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Non_living_resources_repository;
import com.springboot.fyp.root.dao.Resource_type_repository;
import com.springboot.fyp.root.models.Non_Living_Resources;
import com.springboot.fyp.root.models.Resource_type;

@Service
public class Resource_type_service {
	
	@Autowired
	Resource_type_repository resource_type_repository;
	
	@Autowired
	Non_living_resources_repository non_living_resources_repository;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_ResourceTypes_LIST = "ResourceTypesList";
	
	public List<Resource_type> getAll(){
		return resource_type_repository.findAll();
	}
	
	@SuppressWarnings({ "unchecked", "null" })
	public List<Resource_type> getAll(int institute_id){
		List<Resource_type> resource_types = redisUtilityRoot.getList(HASH_KEY_ResourceTypes_LIST+institute_id);
		if(resource_types.size() > 0) {
			return resource_types;
		}else {
			if(non_living_resources_repository.findAll().isEmpty()) {
				return null;
			}else {
				List<Resource_type> final_data = new ArrayList<>() ;
				List<Non_Living_Resources> resource_types_lst = new ArrayList<>();
				for(Non_Living_Resources resources : non_living_resources_repository.findAll()) {
					if(resources.getInstitute_id() == institute_id) {
						resource_types_lst.add(resources);
					}
				}
				List<Resource_type> resourceType = resource_type_repository.findAll();
				
				for(Resource_type type : resourceType) {
					for(Non_Living_Resources resource : resource_types_lst) {
						if(resource.getResource_type_id() == type.getResource_type_id()) {
							final_data.add(type);
							break;
						}
					}
				}
				
				redisUtilityRoot.saveList(final_data, HASH_KEY_ResourceTypes_LIST+institute_id);
				return final_data;
			}
		}
	}
	
}	
