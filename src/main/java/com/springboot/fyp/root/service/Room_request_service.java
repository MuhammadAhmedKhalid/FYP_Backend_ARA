package com.springboot.fyp.root.service;

import java.util.ArrayList;
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
			return roomRequests;
		}
		return new ArrayList<>();
	}
	
}
