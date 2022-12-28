package com.springboot.fyp.root.models;

import lombok.Data;

@Data
public class JWT_Response {
	
	int user_id;
	String email;
	String jwt;
	String admin_name;
	
	public JWT_Response(int user_id, String email, String jwt, String admin_name) {
		this.user_id = user_id;
		this.email = email;
		this.jwt = jwt;
		this.admin_name = admin_name;
	}
	
}
