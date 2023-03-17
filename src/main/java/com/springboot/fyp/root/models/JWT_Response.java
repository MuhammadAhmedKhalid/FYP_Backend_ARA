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
	
	public JWT_Response(int user_id, String email, String jwt, String name, String institute_name, int institute_id, int faculty_id, boolean is_admin) {
		this.user_id = user_id;
		this.email = email;
		this.jwt = jwt;
		this.name = name;
		this.institute_name = institute_name;
		this.institute_id = institute_id;
		this.faculty_id = faculty_id;
		this.is_admin = is_admin;
	}
	
}
