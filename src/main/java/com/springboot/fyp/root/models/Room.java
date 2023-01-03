package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Room")
@Data
public class Room {
	
	public Room(int room_id, String name, int department_id) {
		this.room_id = room_id;
		this.name = name;
		this.department_id = department_id;
	}
	@Id
	private int room_id;
	private String name;
	private int department_id;
	
}
