package com.springboot.fyp.root.models;

import lombok.Data;

@Data
public class TeacherResult {
	
	public TeacherResult(int faculty_id, double totalJaccardSimilarity) {
		super();
		this.faculty_id = faculty_id;
		this.jaccardSimilarity = totalJaccardSimilarity;
	}
	private int faculty_id;
    private double jaccardSimilarity;
	
}
