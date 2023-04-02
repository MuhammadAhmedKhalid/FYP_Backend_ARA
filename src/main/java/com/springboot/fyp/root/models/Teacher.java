package com.springboot.fyp.root.models;

import lombok.Data;

@Data
public class Teacher {
	
	public Teacher(int faculty_id, int yearsOfExperience) {
		super();
		this.faculty_id = faculty_id;
		this.yearsOfExperience = yearsOfExperience;
	}
	
	private int faculty_id;
    private int yearsOfExperience;
	
}
