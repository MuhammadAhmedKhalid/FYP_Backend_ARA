package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Resource_occupied")
@Data
public class Resource_occupied {
	
	@Transient
	public final String SEQUENCE_NAME="Resource_occupied_sequence";
	
	@Id
	private int occupied_resource_id;
	private int resource_type_id;
	private int room_id;
	private int quantity;
	private String startTime;
	private String endTime;
	private String date;
	
	public Resource_occupied(int resource_type_id, int room_id, int quantity, String startTime,
			String endTime, String date) {
		this.resource_type_id = resource_type_id;
		this.room_id = room_id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		this.quantity = quantity;
	}
	
	
	
}
