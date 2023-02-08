package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Resource_type")
@Data
public class Resource_type {
	
	@Transient
	public final String SEQUENCE_NAME="ResourceType_sequence";
	
	@Id
	private int resource_type_id;
	private String object_name;
	private int institute_id;
	
}
