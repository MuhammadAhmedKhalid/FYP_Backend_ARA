package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "AssignedCourse")
@Data
public class AssignedCourse {
	
	@Transient
	public final String SEQUENCE_NAME="AssignedCourse_sequence";
	
	@Id
	private int assignedCourseId;
	private int batchId;
	private int course_id;
	private String day;
	private int department_id;
	private String startTime;
	private String endTime;
	private int faculty_id;
	private int institute_id;
	private int room_id;
	private String semesterType;
	
}
