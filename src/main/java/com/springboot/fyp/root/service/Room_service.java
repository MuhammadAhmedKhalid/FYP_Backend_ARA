package com.springboot.fyp.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Room_repository;
import com.springboot.fyp.root.models.Room;

@Service
public class Room_service {
	
	@Autowired
	Room_repository room_repository;

	public String insert(List<Room> roomList) {
		for(Room room :roomList ) {
			room_repository.insert(room);
		}
		return "Operation performed successfully.";
	}
	
	public List<Room> getAll(){
		return room_repository.findAll();
	}
	
}
