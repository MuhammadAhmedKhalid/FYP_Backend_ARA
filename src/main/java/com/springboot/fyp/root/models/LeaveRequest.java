package com.springboot.fyp.root.models;

import lombok.Data;

@Data
public class LeaveRequest {
	
	private String reason;
	private int institute_id;
	private int faculty_id;
	private int department_id;
	private int user_id;
	private int requested_faculty_id;
	private String date;
	private String startTime;
	private String endTime;
	
}
