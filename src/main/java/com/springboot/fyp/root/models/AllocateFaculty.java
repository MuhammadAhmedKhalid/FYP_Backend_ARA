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
	private int faculty_id;
	private int institute_id;
}
