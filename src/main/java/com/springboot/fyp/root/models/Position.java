package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Position")
@Data
public class Position {

	@Transient
	public final String SEQUENCE_NAME="Position_sequence";
	
	@Id
	private int position_id;
	private String position_name;
	private int institute_id;
	
}
