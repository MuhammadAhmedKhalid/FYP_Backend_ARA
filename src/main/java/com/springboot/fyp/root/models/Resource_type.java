package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Resource_type")
@Data
public class Resource_type {
	
	public Resource_type(int resource_type_id, String name) {
		this.resource_type_id = resource_type_id;
		this.name = name;
	}
	@Id
	private int resource_type_id;
	private String name;
	
}
