package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Leave")
@Data
public class Leave {
	
	@Transient
	public final String SEQUENCE_NAME="Leave_sequence";
	
	@Id
	private int leaveId;
	private String reason;
	private int institute_id;
	private int faculty_id;
	private String date;
	private String startTime;
	private String endTime;
	
}
