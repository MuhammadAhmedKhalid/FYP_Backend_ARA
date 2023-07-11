package com.springboot.fyp.faculty.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.springboot.fyp.root.models.User;

import lombok.Data;

@Document(collection = "Faculty")
@Data
public class Faculty {
	
	@Transient
	public final String SEQUENCE_NAME="Faculty_sequence";
	
	@Id
	private int faculty_id;
	private String name;
	private String code;
	private String phone_number;
	private String officialEmailAddress;
	private int department_id;
	private List<String> specialization;
	private String designation;
	private int institute_id;
	User user;
	private int yearsOfExperience;
	
}
