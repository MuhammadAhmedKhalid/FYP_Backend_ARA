package com.springboot.fyp.root.models;

import java.util.List;

import lombok.Data;

@Data
public class RequestedLeave {

	private LeaveRequest leaveRequest;
	private List<Course> coursesList;
	private List<AvailableFacultyList> availableFacultyList;
	
}
