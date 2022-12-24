package com.springboot.fyp.root.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Institute")
@Data
public class Institute {
	@Id
	private int institue_id;
	private String name;
	private int institute_type_id;
}
