package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Batch")
@Data
public class Batch {
	
	@Transient
	public final String SEQUENCE_NAME="Batch_sequence";
	
	@Id
	private int batchId;
	private int batchYear;
//	private String batchType;
	private int department_id;
	private int institute_id;
	
}
