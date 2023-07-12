package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Room_repository;
import com.springboot.fyp.root.models.Room;

@Service
public class Room_service {
	
	@Autowired
	Room_repository room_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_ROOMS_LIST = "RoomsList";

	public String insert(Room room) {
		
		for(Room rooms: room_repository.findAll()) {
			if(rooms.getRoom_name().equalsIgnoreCase(room.getRoom_name()) && rooms.getDepartment_id() == room.getDepartment_id()) {
				return null;
			}
		}
		
		room.setRoom_id(sequenceGeneratorService.getSequenceNumber(room.SEQUENCE_NAME));
		room_repository.insert(room);
		redisUtilityRoot.deleteList(HASH_KEY_ROOMS_LIST+room.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Room> getAll(int institute_id){
		List<Room> rooms = redisUtilityRoot.getList(HASH_KEY_ROOMS_LIST+institute_id);
		if(rooms.size() > 0) {
			return rooms;
		}else {
			if(room_repository.findAll().isEmpty()) {
				return null;
			} else {
				List<Room> rooms_lst = new ArrayList<>();
				for(Room room : room_repository.findAll()) {
					if(room.getInstitute_id() == institute_id) {
						rooms_lst.add(room);
					}
				}
				redisUtilityRoot.saveList(rooms_lst, HASH_KEY_ROOMS_LIST+institute_id);
				return rooms_lst;
			}
		}
	}
	
	public String update(int room_id, Room bodyRoom) {
		List<Room> rooms = room_repository.findAll();
		
		for(Room room : rooms) {
			if(room_id != room.getRoom_id()
					&& (room.getDepartment_id() == bodyRoom.getDepartment_id()
							&& room.getRoom_name().equalsIgnoreCase(bodyRoom.getRoom_name()))) {
				return null;
			}
		}
		
		for(Room room : rooms) {
			if(room.getRoom_id() == room_id) {
				room.setRoom_name(bodyRoom.getRoom_name());
				room.setLocation(bodyRoom.getLocation());
				room.setArea(bodyRoom.getArea());
				room.setDepartment_id(bodyRoom.getDepartment_id());
				room_repository.save(room);
				break;
			}
		}
		
		redisUtilityRoot.deleteList(HASH_KEY_ROOMS_LIST+bodyRoom.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	public String delete(int room_id) {
		int institute_id= 0;
		List<Room> rooms = room_repository.findAll();
		for(Room room : rooms) {
			if(room.getRoom_id() == room_id) {
				institute_id = room.getInstitute_id();
				room_repository.deleteById(room_id);
				break;
			}
		}
		redisUtilityRoot.deleteList(HASH_KEY_ROOMS_LIST+institute_id);
		return "Deleted successfully.";
	}
	
}
