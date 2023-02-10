package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Specialization")
@Data
public class Specialization {

	@Transient
	public final String SEQUENCE_NAME="Specialization_sequence";
	
	private int specialization_id;
	private String specialization_name;
	private int department_id;
	
}
