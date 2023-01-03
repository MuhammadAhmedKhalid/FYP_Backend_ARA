package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Non_Living_Resources")
@Data
public class Non_Living_Resources {
	
	public Non_Living_Resources(int resource_id, String description, int quantity, int resource_type_id, int room_id) {
		this.resource_id = resource_id;
		this.description = description;
		this.quantity = quantity;
		this.resource_type_id = resource_type_id;
		this.room_id = room_id;
	}
	
	@Id
	private int resource_id;
	private String description;
	private int quantity;
	private int resource_type_id;
	private int room_id;
	
}
