package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Department")
@Data
public class Department {
	
	public Department(int department_id, String department_name, int institue_id) {
		this.department_id = department_id;
		this.department_name = department_name;
		this.institue_id = institue_id;
	}
	
	@Id
	private int department_id;
	private String department_name;
	private int institue_id;
	
}
