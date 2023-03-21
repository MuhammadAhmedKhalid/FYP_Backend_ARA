package com.springboot.fyp.root.models;

import lombok.Data;

@Data
public class JWT_Response {
	
	int user_id;
	String email;
	String jwt;
	String name;
	String institute_name;
	int institute_id;
	int faculty_id;
	boolean is_admin;
	String springStartMonth;
	String springEndMonth;
	String fallStartMonth;
	String fallEndMonth;
	
	public JWT_Response(int user_id, String email, String jwt, String name, String institute_name, 
			int institute_id, int faculty_id, boolean is_admin,
			String springStartMonth, String springEndMonth, String fallStartMonth, String fallEndMonth) {
		this.user_id = user_id;
		this.email = email;
		this.jwt = jwt;
		this.name = name;
		this.institute_name = institute_name;
		this.institute_id = institute_id;
		this.faculty_id = faculty_id;
		this.is_admin = is_admin;
		this.springStartMonth = springStartMonth;
		this.springEndMonth = springEndMonth;
		this.fallStartMonth = fallStartMonth;
		this.fallEndMonth = fallEndMonth;
	}
	
}
