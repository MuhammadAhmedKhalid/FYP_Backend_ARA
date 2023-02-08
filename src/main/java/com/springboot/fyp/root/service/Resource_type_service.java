package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Resource_type_repository;
import com.springboot.fyp.root.models.Resource_type;

@Service
public class Resource_type_service {
	
	@Autowired
	Resource_type_repository resource_type_repository;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_ResourceTypes_LIST = "ResourceTypesList";
	
	public List<Resource_type> getAll(){
		return resource_type_repository.findAll();
	}
	
	@SuppressWarnings("unchecked")
	public List<Resource_type> getAll(int institute_id){
		List<Resource_type> resource_types = redisUtilityRoot.getList(HASH_KEY_ResourceTypes_LIST+institute_id);
		if(resource_types.size() > 0) {
			return resource_types;
		}else {
			if(resource_type_repository.findAll().isEmpty()) {
				return null;
			}else {
				List<Resource_type> resource_types_lst = new ArrayList<>();
				for(Resource_type resources : resource_type_repository.findAll()) {
					if(resources.getInstitute_id() == institute_id) {
						resource_types_lst.add(resources);
					}
				}
				redisUtilityRoot.saveList(resource_types_lst, HASH_KEY_ResourceTypes_LIST+institute_id);
				return resource_types_lst;
			}
		}
	}
	
}	
