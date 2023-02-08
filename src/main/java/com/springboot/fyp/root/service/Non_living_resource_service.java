package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Non_living_resources_repository;
import com.springboot.fyp.root.models.Non_Living_Resources;

@Service
public class Non_living_resource_service {
	
	@Autowired
	Non_living_resources_repository non_living_resources_repository;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_NonLivingResources_LIST = "NonLivingResourcesList";
	
	public List<Non_Living_Resources> getAll(){
		List<Non_Living_Resources> resources = new ArrayList<>();
 		for (Non_Living_Resources resource : non_living_resources_repository.findAll()) {
			if(resource.getQuantity() != 0) {
				resources.add(resource);
			}
		}
 		return resources;
	}
	
	@SuppressWarnings("unchecked")
	public List<Non_Living_Resources> getAll(int institute_id){
		List<Non_Living_Resources> non_Living_Resources = redisUtilityRoot.getList(HASH_KEY_NonLivingResources_LIST+institute_id);
		if(non_Living_Resources.size() > 0) {
			return non_Living_Resources;
		}else {
			if(non_living_resources_repository.findAll().isEmpty()) {
				return null;
			}else {
				List<Non_Living_Resources> non_Living_Resources_lst = new ArrayList<>();
				for(Non_Living_Resources resources : non_living_resources_repository.findAll()) {
					if(resources.getInstitute_id() == institute_id) {
						non_Living_Resources_lst.add(resources);
					}
				}
				redisUtilityRoot.saveList(non_Living_Resources_lst, HASH_KEY_NonLivingResources_LIST+institute_id);
				return non_Living_Resources_lst;
			}
		}
	}
	
	public int getQuantity(int resource_type_id) {
		for(Non_Living_Resources resources : getAll()) {
			if(resources.getResource_type_id() == resource_type_id) {
				return resources.getQuantity();
			}
		}
		return 0;
	}
	
}
