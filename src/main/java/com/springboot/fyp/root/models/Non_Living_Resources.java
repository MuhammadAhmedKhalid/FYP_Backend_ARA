package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Non_Living_Resources")
@Data
public class Non_Living_Resources {
	
	@Transient
	public final String SEQUENCE_NAME="NonLivingResources_sequence";
	
	@Id
	private int resource_id;
	private int quantity;
	private int resource_type_id;
	private int room_id;
	private int institute_id;
	
}
