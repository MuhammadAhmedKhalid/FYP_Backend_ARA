package com.springboot.fyp.root.models;

import lombok.Data;

@Data
public class AddInstituteResponse {
	
	private int institute_id;
	private String institute_name;
	private String springStartMonth;
	private String springEndMonth;
	private String fallStartMonth;
	private String fallEndMonth;
	private String instituteStartTime;
	private String instituteEndTime;
}
