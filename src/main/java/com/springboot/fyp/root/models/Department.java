package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Department")
@Data
public class Department {
	
	@Transient
	public final String SEQUENCE_NAME="Department_sequence";
	
	@Id
	private int department_id;
	private String department_name;
	private int institute_id;
	
}
