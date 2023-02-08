package com.springboot.fyp.root.models;

import lombok.Data;

@Data
public class Add_object {
	
	private String object_name;
	private int quantity;
	private int resource_type_id;
	private int room_id;
	private int institute_id;

}
