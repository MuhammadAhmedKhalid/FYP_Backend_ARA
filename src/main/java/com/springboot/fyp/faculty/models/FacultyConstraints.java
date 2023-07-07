package com.springboot.fyp.faculty.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
class Time{
	private String startTime;
    private String endTime;
}

@Data
class Availbility{
	private Time monday;
	private Time tuesday;
	private Time wednesday;
	private Time thursday;
	private Time friday;
	private Time saturday;
}

@Data
class Day{
	private Break monday;
	private Break tuesday;
	private Break wednesday;
	private Break thursday;
	private Break friday;
	private Break saturday;
}

@Data
class Break{
	private Time break1;
	private Time break2;
}

@Document(collection = "FacultyConstraints")
@Data
public class FacultyConstraints {

	@Transient
	public final String SEQUENCE_NAME="FacultyConstraints_sequence";
	
	@Id
	private int facultyConstraintId;
	private int faculty_id;
	private int institute_id;
	private int user_id;
	private String applicableStartDate;
	private String applicableEndDate;
	private Availbility availability;
	private Day breaks;
	
}
