package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Course_repository;
import com.springboot.fyp.root.models.Course;

@Service
public class Course_service {
	
	@Autowired
	Course_repository course_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_COURSE_LIST = "CourseList";
	
	public String add(Course course) {
		
		for(Course crs: course_repository.findAll()) {
			if(crs.getCourse_name().equals(course.getCourse_name())
					&& crs.getDepartment_id() == course.getDepartment_id()) {
				return null;
			}
		}
		
		course.setCourse_id(sequenceGeneratorService.getSequenceNumber(course.SEQUENCE_NAME));
		course_repository.insert(course);
		redisUtilityRoot.deleteList(HASH_KEY_COURSE_LIST+course.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getAll(int institute_id){
		List<Course> courses = redisUtilityRoot.getList(HASH_KEY_COURSE_LIST+institute_id);
		if(courses.size() > 0) {
			return courses;
		}else {
			List<Course> course_lst = course_repository.findAll();
			if(course_lst.isEmpty()) {
				return null;
			}else {
				List<Course> courses_lst = new ArrayList<>();
				for(Course course : course_lst) {
					if(course.getInstitute_id() == institute_id) {
						courses_lst.add(course);
					}
				}
				redisUtilityRoot.saveList(courses_lst, HASH_KEY_COURSE_LIST+institute_id);
				return courses_lst;
			}
		}
	}
	
}
