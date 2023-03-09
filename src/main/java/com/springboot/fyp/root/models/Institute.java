package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Institute")
@Data
public class Institute {
	
	@Transient
	public final String SEQUENCE_NAME="Institute_sequence";
	
	@Id
	private int institute_id;
	private String institute_name;
	private int institute_type_id;
	private String branch;
	private String address;
	private String contact;
	public int user_id;
	public String springStartMonth;
	public String springEndMonth;
	public String fallStartMonth;
	public String fallEndMonth;
}
