package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Institute_type")
@Data
public class Institute_type {
	
	public Institute_type(){}
	
	public Institute_type(int institute_type_id, String name) {
		this.institute_type_id = institute_type_id;
		this.name = name;
	}
	public Institute_type(int institute_type_id, String name, String domain) {
		this.institute_type_id = institute_type_id;
		this.name = name;
		this.domain = domain;
	}
	
	@Id
	private int institute_type_id;
	private String name;
	private String domain;
	
}
