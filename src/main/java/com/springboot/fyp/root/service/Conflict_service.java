package com.springboot.fyp.root.service;

import org.springframework.stereotype.Service;

import com.springboot.fyp.root.models.Conflict;

@Service
public class Conflict_service {
	
	 public boolean hasConflict(Conflict conflict1, Conflict conflict2) {
	        if (conflict1.getEndTime().before(conflict2.getStartTime()) || 
	        		conflict1.getStartTime().after(conflict2.getEndTime())) {
	            return false;
	        } else {
	            return true;
	        }
	    }
	
}
