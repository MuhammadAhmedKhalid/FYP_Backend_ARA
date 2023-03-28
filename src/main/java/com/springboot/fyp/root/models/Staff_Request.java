package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Staff_request")
@Data
public class Staff_Request {
	
	@Transient
	public final String SEQUENCE_NAME="Staff_request_sequence";
	
	@Id
	private int staff_req_id;
	private int department_id;
	private int institute_id;
	private int user_id;
	private int room_id;
	private int requested_faculty_id;
	private String startTime;
	private String endTime;
	private String date;
	private int leaveId;
	
}
