package com.springboot.fyp.root.models;

import lombok.Data;

@Data
public class Teacher {
	
	public Teacher(String name, int experience) {
		super();
		this.name = name;
		this.experience = experience;
	}
	
	private String name;
    private int experience;
	
}
