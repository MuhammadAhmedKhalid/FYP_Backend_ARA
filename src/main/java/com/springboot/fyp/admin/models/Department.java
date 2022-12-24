package com.springboot.fyp.admin.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Department")
@Data
public class Department {
	
	@Id
	private int department_id;
	private String department_name;
	private int institue_id;
	
}
