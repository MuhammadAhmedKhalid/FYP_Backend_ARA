package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "OfferCourse")
@Data
public class OfferCourse {
	
	@Transient
	public final String SEQUENCE_NAME="OfferCourse_sequence";
	
	@Id
	private int offerCourseId;
	private int course_id;
	private int batchId;
	private int department_id;
	private String semester;
	private int institute_id;
	private boolean allocated;
	private boolean addedInTimetable;

}
