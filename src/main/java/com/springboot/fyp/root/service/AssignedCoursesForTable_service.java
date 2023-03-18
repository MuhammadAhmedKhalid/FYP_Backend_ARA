package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.AssignedCoursesForTable_repository;
import com.springboot.fyp.root.models.AssignedCoursesForTable;

@Service
public class AssignedCoursesForTable_service {
	
	@Autowired
	AssignedCoursesForTable_repository assignedCoursesForTable_repository;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_ASSIGNED_COURSES_FOR_TABALE_LIST = "AssignedCoursesForTableList";
	
	@SuppressWarnings("unchecked")
	public List<AssignedCoursesForTable> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_ASSIGNED_COURSES_FOR_TABALE_LIST+institute_id).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_ASSIGNED_COURSES_FOR_TABALE_LIST+institute_id);
		}else {
			if(assignedCoursesForTable_repository.findAll().isEmpty()) {
				return null;
			}else {
				List<AssignedCoursesForTable> assignedCourses = new ArrayList<>();
				for(AssignedCoursesForTable course : assignedCoursesForTable_repository.findAll()) {
					if(course.getInstituteId() == institute_id) {
						assignedCourses.add(course);
					}
				}
				redisUtilityRoot.saveList(assignedCourses, HASH_KEY_ASSIGNED_COURSES_FOR_TABALE_LIST+institute_id);
				return assignedCourses;
			}
		}
	}
	
}
