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
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_ROOM_REQUESTS = "RoomRequests";
	
	public String add(Room_Request room_Request) {
		room_Request.setRoom_req_id(sequenceGeneratorService.getSequenceNumber(room_Request.SEQUENCE_NAME));
		room_request_repository.insert(room_Request);
		redisUtilityRoot.deleteList(HASH_KEY_ROOM_REQUESTS+room_Request.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Room_Request> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_ROOM_REQUESTS+institute_id).size()>0) {
			return redisUtilityRoot.getList(HASH_KEY_ROOM_REQUESTS+institute_id);
		}else {
			
			List<Room_Request> roomRequests = new ArrayList<>();
			for(Room_Request requests : room_request_repository.findAll()) {
				if(requests.getInstitute_id() == institute_id) {
					roomRequests.add(requests);
				}
			}
			redisUtilityRoot.saveList(roomRequests, HASH_KEY_ROOM_REQUESTS+institute_id);
			return roomRequests;
		}
	}
	
	public String delete(int room_req_id) {
		List<Room_Request> roomRequests = room_request_repository.findAll();
		int institute_id = 0;
		for(Room_Request requests : roomRequests) {
			if(requests.getRoom_req_id() == room_req_id) {
				institute_id = requests.getInstitute_id();
				room_request_repository.deleteById(room_req_id);
				redisUtilityRoot.deleteList(HASH_KEY_ROOM_REQUESTS+institute_id);
				redisUtilityRoot.saveList(room_request_repository.findAll(), HASH_KEY_ROOM_REQUESTS+institute_id);
				return "Requested room request deleted.";
			}
		}
		return null;
	}
	
}
