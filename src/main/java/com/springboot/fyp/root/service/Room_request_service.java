package com.springboot.fyp.root.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Room_request_repository;
import com.springboot.fyp.root.models.Room_Request;

@Service
public class Room_request_service {
	
	@Autowired
	Room_request_repository room_request_repository;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	public String add(Room_Request room_Request) {
		
		room_Request.setRoom_req_id(sequenceGeneratorService.getSequenceNumber(room_Request.SEQUENCE_NAME));
		room_request_repository.insert(room_Request);
		return "Operation performed successfully.";
		
	}
	
	public List<Room_Request> getAll(){
		List<Room_Request> roomRequests = room_request_repository.findAll();
		if(roomRequests.size() != 0) {
			List<Room_Request> requested_rooms = new ArrayList<>();
			for(Room_Request room_Request: roomRequests) {	
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
			    Date date_time = new Date();  
			    int result = room_Request.getDate().compareTo(formatter.format(date_time));
			    if(result < 0) {
			    	room_request_repository.deleteById(room_Request.getRoom_req_id());
			    }else {
			    	requested_rooms.add(room_Request);
			    }
			}
			return requested_rooms;
		}
		return new ArrayList<>();
		
	}
	
}
