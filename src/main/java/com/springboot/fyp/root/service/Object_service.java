package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Non_living_resources_repository;
import com.springboot.fyp.root.dao.Resource_type_repository;
import com.springboot.fyp.root.models.Object;
import com.springboot.fyp.root.models.Non_Living_Resources;
import com.springboot.fyp.root.models.Resource_type;

@Service
public class Object_service {

	@Autowired
	Resource_type_repository resource_type_repository;
	
	@Autowired
	Non_living_resources_repository non_living_resources_repository;
	
	@Autowired
	Non_living_resource_service non_living_resource_service;
	
	@Autowired
	Resource_type_service resource_type_service;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_NonLivingResources_LIST = "NonLivingResourcesList";
	public static final String HASH_KEY_ResourceTypes_LIST = "ResourceTypesList";
	public static final String HASH_KEY_Objects_LIST = "ObjectsList";
	
	public String insert(Object object) {
		
		int resource_id = 0;
		
		List<Resource_type> allResources = resource_type_service.getAll();
		for(Resource_type resource : allResources) {
			if(resource.getObject_name().equals(object.getObject_name())) {
				resource_id = resource.getResource_type_id();
				System.out.println(resource_id);
				break;
			}
		}
		
		if(resource_id != 0) {
			for(Non_Living_Resources resource : non_living_resource_service.getAll()) {
				if(resource.getResource_type_id() == resource_id) {
					if(object.getRoom_id() == resource.getRoom_id()
							&& object.getDepartment_id() == resource.getDepartment_id()) {
						return null;
					}
				}
			}
		}
		
		if(resource_id != 0) {
			for(Non_Living_Resources resource : non_living_resource_service.getAll()) {
				if(resource.getResource_type_id() == resource_id) {
					if(object.getRoom_id() != resource.getRoom_id()
							|| object.getDepartment_id() != resource.getDepartment_id()) {
						
						Non_Living_Resources non_Living_Resources = new Non_Living_Resources();
						non_Living_Resources.setResource_id(sequenceGeneratorService.getSequenceNumber(non_Living_Resources.SEQUENCE_NAME));
						non_Living_Resources.setResource_type_id(resource_id);
						non_Living_Resources.setQuantity(object.getQuantity());
						non_Living_Resources.setRoom_id(object.getRoom_id());
						non_Living_Resources.setInstitute_id(object.getInstitute_id());
						non_Living_Resources.setDepartment_id(object.getDepartment_id());
						non_living_resources_repository.insert(non_Living_Resources);
						
						redisUtilityRoot.deleteList(HASH_KEY_Objects_LIST+object.getInstitute_id());
						redisUtilityRoot.deleteList(HASH_KEY_NonLivingResources_LIST+object.getInstitute_id());
						redisUtilityRoot.deleteList(HASH_KEY_ResourceTypes_LIST+object.getInstitute_id());
						
						return "Operation performed successfully.";
					}
				}
			}
		}
		
		Resource_type resourceType = new Resource_type();
		resourceType.setResource_type_id(sequenceGeneratorService.getSequenceNumber(resourceType.SEQUENCE_NAME));
		resourceType.setObject_name(object.getObject_name());
		resourceType.setInstitute_id(object.getInstitute_id());
		resource_type_repository.insert(resourceType);
		
		Non_Living_Resources non_Living_Resources = new Non_Living_Resources();
		non_Living_Resources.setResource_id(sequenceGeneratorService.getSequenceNumber(non_Living_Resources.SEQUENCE_NAME));
		non_Living_Resources.setResource_type_id(resourceType.getResource_type_id());
		non_Living_Resources.setQuantity(object.getQuantity());
		non_Living_Resources.setRoom_id(object.getRoom_id());
		non_Living_Resources.setInstitute_id(object.getInstitute_id());
		non_Living_Resources.setDepartment_id(object.getDepartment_id());
		non_living_resources_repository.insert(non_Living_Resources);
		
		redisUtilityRoot.deleteList(HASH_KEY_Objects_LIST+object.getInstitute_id());
		redisUtilityRoot.deleteList(HASH_KEY_NonLivingResources_LIST+object.getInstitute_id());
		redisUtilityRoot.deleteList(HASH_KEY_ResourceTypes_LIST+object.getInstitute_id());
		
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_Objects_LIST+institute_id).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_Objects_LIST+institute_id);
		} else {
			List<String> objects = new ArrayList<>();
			for(Non_Living_Resources resource : non_living_resource_service.getAll()) {
				for(Resource_type resource_type : resource_type_service.getAll()) {
					if(resource.getResource_type_id() == resource_type.getResource_type_id()) {
						objects.add(resource_type.getObject_name());
					}
				}
			}
			redisUtilityRoot.saveList(objects, HASH_KEY_Objects_LIST+institute_id);
			return objects;
		}
	}
	
}
