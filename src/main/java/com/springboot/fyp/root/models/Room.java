package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Room")
@Data
public class Room {
	
	@Transient
	public final String SEQUENCE_NAME="Room_sequence";
	
	@Id
	private int room_id;
	private String room_name;
	private String location;
	private String area;
	private int department_id;
	private int institute_id;
	
}
