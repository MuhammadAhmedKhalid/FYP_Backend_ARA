package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "AssignedCoursesForTable")
@Data
public class AssignedCoursesForTable {

	@Transient
	public final String SEQUENCE_NAME="AssignedCourseForTable_sequence";
	
	@Id
	private int assignedCoursesId;
	private int courseId;
	private int departmentId;
	private int facultyId;
	private int batchId;
	private String semesterType;
	private int instituteId;
	
}
