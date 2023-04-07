package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Notification")
@Data
public class Notification {
	
	@Transient
	public final String SEQUENCE_NAME="Notification_sequence";
	
	@Id
	private int notificationId;
	private String title;
	private String date;
	private String details;
	private int department_id;
	private int institute_id;

}
