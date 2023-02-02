package com.springboot.fyp.root.service;

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
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_ROOM_REQUESTS = "RoomRequests";
	
	public String add(Room_Request room_Request) {
		room_Request.setRoom_req_id(sequenceGeneratorService.getSequenceNumber(room_Request.SEQUENCE_NAME));
		room_request_repository.insert(room_Request);
		redisUtilityRoot.deleteList(HASH_KEY_ROOM_REQUESTS);
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Room_Request> getAll(){
		if(redisUtilityRoot.getList(HASH_KEY_ROOM_REQUESTS).size()>0) {
			return redisUtilityRoot.getList(HASH_KEY_ROOM_REQUESTS);
		}else {
			List<Room_Request> roomRequests = room_request_repository.findAll();
			redisUtilityRoot.saveList(roomRequests, HASH_KEY_ROOM_REQUESTS);
			return redisUtilityRoot.getList(HASH_KEY_ROOM_REQUESTS);
		}
	}
	
}
