package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Non_living_resources_repository;
import com.springboot.fyp.root.models.Non_Living_Resources;

@Service
public class Non_living_resource_service {
	
	@Autowired
	Non_living_resources_repository non_living_resources_repository;
	
	public String insert(List<Non_Living_Resources> resourcesList){
		for (Non_Living_Resources non_living_resource : resourcesList) {
			non_living_resources_repository.insert(non_living_resource);
		}
		return "Operation performed successfully.";
	}
	
	public List<Non_Living_Resources> getAll(){
//		return non_living_resources_repository.findAll();
		List<Non_Living_Resources> resources = new ArrayList<>();
 		for (Non_Living_Resources resource : non_living_resources_repository.findAll()) {
			if(resource.getQuantity() != 0) {
				resources.add(resource);
			}
		}
 		return resources;
	}
	
	public int getQuantity(int resource_type_id) {
		for(Non_Living_Resources resources : getAll()) {
			if(resources.getResource_type_id() == resource_type_id) {
				return resources.getQuantity();
			}
		}
		return 0;
	}
	
	public void updateQuantity(int resource_type_id, int quantity) {
		for(Non_Living_Resources resources : getAll()) {
			if(resources.getResource_type_id() == resource_type_id) {
				Optional<Non_Living_Resources> non_Living_Resources = non_living_resources_repository.findById(resources.getResource_id());
				Non_Living_Resources non_Living_Resource = non_Living_Resources.get();
				non_Living_Resource.setQuantity(quantity);
				non_living_resources_repository.save(non_Living_Resource);
			}
		}
	}
	
}
