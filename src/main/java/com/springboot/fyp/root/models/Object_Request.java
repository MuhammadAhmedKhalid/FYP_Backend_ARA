package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Object_request")
@Data
public class Object_Request {
	
	@Transient
	public final String SEQUENCE_NAME="Object_request_sequence";
	
	@Id
	private int obj_req_id;
	private int department_id;
	private int room_id;
	private int resource_type_id;
	private int quantity;
	private String startTime;
	private String endTime;
	private String date;
	
}
