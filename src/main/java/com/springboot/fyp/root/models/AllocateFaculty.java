package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "AllocateFaculty")
@Data
public class AllocateFaculty {

	@Transient
	public final String SEQUENCE_NAME="AllocateFaculty_sequence";
	
	@Id
	private int allocateFacultyId;
	private int offerCourseId;
	private int department_id;
	private int batchId;
	private int institute_id;
	private int faculty_id;
	
}
