package com.springboot.fyp.root.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.models.MakeResBusy;
import com.springboot.fyp.root.models.Room_Request;
import com.springboot.fyp.root.models.Staff_Request;

@Service
public class MakeResBusy_service {

	@Autowired
	Room_request_service room_request_service;
	
	@Autowired
	Staff_request_service staff_request_service;

	public String add(MakeResBusy makeResBusy) {
		
		ArrayList<String> dates = makeResBusy.getDates_lst();
		
		for(int i=0; i<makeResBusy.getDates_lst().size(); i++) {
			Room_Request room_Request = makeResBusy.getRoom_Request();
			Staff_Request staff_Request = makeResBusy.getStaff_Request();
			room_Request.setDate(dates.get(i));
			staff_Request.setDate(dates.get(i));
			room_request_service.add(room_Request);
			staff_request_service.add(staff_Request);
		}
		
		return "Operation performed successfully.";
	}
	
}
