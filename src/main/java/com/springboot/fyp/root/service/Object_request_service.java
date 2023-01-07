package com.springboot.fyp.root.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Object_request_repository;
import com.springboot.fyp.root.models.Object_Request;

@Service
public class Object_request_service {

	@Autowired
	Object_request_repository object_request_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	Non_living_resource_service non_living_resource_service;
	
	// update quantity according to date and time
	public void update() {
		List<Object_Request> request_list = object_request_repository.findAll();
		for (Object_Request request : request_list) {

			int endDateMonth = Integer.parseInt(request.getEndDate().substring(0, 2));
			int endDateDay = Integer.parseInt(request.getEndDate().substring(3, 5));
			int endDateYear = Integer.parseInt(request.getEndDate().substring(6));
			
			int endTimeHours = Integer.parseInt(request.getEndTime().substring(0, 2));
			int endTimeMins = Integer.parseInt(request.getEndTime().substring(3));
			

			LocalDate currentDate = LocalDate.now(); // current date
			LocalTime currentTime = LocalTime.now(); // current time
			LocalDate endDate = LocalDate.of(endDateYear, endDateMonth, endDateDay); // resource endDate
			LocalTime endTime = LocalTime.of(endTimeHours, endTimeMins); // resource endTime
			
//			System.out.println(currentDate);
//	    	System.out.println(endDate);
			
	    	if ((endDate.isEqual(currentDate) || endDate.isAfter(currentDate)) && 
	    			(endTime.compareTo(currentTime) >= 0)) {
			      System.out.println("The date is equal to or greater than today's date");
			    } else {
			      System.out.println("Resource is free");
			      non_living_resource_service.addQuantity(request.getResource_type_id(), request.getQuantity());
			      object_request_repository.deleteById(request.getObj_req_id());
			    }
		}
	}

	public String add(Object_Request object_Request) {
		object_Request.setObj_req_id(sequenceGeneratorService.getSequenceNumber(object_Request.SEQUENCE_NAME));
		
		int available_quantity = non_living_resource_service.getQuantity(object_Request.getResource_type_id());
		// first check if available according to date and then time
		if(object_Request.getQuantity() > available_quantity) {
			update();
			return null;
		}
		else {
			// minus quantity according to date and time
			int quantity = available_quantity - object_Request.getQuantity();
			non_living_resource_service.updateQuantity(object_Request.getResource_type_id(), quantity);
		}
		object_request_repository.insert(object_Request);
		return "Operation performed successfully.";
	}
	
}
