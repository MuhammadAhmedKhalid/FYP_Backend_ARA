package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Course")
@Data
public class Course {

	@Transient
	public final String SEQUENCE_NAME="Course_sequence";
	
	@Id
	private int course_id;
	private String course_name;
	private int department_id;
	private int institute_id;
	
}
