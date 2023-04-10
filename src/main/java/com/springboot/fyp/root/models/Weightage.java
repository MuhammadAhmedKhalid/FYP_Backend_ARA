package com.springboot.fyp.root.models;

import java.util.List;

import lombok.Data;

@Data
public class Weightage {
	
	private int institute_id;
	private List<List<JaccardResult>> jaccardResults;
	private List<AssignedCourse> assignedCourse;
	
}
