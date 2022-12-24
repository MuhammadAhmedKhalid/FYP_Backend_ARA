package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "User")
@Data
public class User {
	
	@Transient
	public final String SEQUENCE_NAME="User_sequence";
	
	@Id
	private int user_id;
	private String email;
	private String password; 
	private String last_loggedIn;
}
