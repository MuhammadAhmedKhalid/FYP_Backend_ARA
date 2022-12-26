package com.springboot.fyp.root.models;

import lombok.Data;

@Data
public class JWT_Response {
	
	int user_id;
	String email;
	String jwt;
	
	public JWT_Response(int user_id, String email, String jwt) {
		this.user_id = user_id;
		this.email = email;
		this.jwt = jwt;
	}
	
}
