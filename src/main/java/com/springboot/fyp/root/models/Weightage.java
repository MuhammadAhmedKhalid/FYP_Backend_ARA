package com.springboot.fyp.root.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Weightage")
@Data
public class Weightage {
	
	@Transient
	public final String SEQUENCE_NAME="Weightage_sequence";
	
	@Id
	private int weightageId;
	private int institute_id;
	List<JaccardResult> jaccardResults;
	private AssignedCourse assignedCourse;
	
}
