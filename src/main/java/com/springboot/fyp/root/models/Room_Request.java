package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Room_request")
@Data
public class Room_Request {

	@Transient
	public final String SEQUENCE_NAME="Room_request_sequence";
	
	@Id
	private int room_req_id;
	private int assignedCoursesId;
	private int department_id;
	private int institute_id;
	private int user_id;
	private int room_id;
	private String startTime;
	private String endTime;
	private String date;
	
}
