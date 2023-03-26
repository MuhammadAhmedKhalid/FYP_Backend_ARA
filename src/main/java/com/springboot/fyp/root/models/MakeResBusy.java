package com.springboot.fyp.root.models;

import java.util.ArrayList;

import lombok.Data;

@Data
public class MakeResBusy {

	private Room_Request room_Request;
	private Staff_Request staff_Request;
	private ArrayList<String> dates_lst;
	
}
