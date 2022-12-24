package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Institute_type")
@Data
public class Institute_type {
	
	@Id
	private int institute_type_id;
	private String name;
	
}
