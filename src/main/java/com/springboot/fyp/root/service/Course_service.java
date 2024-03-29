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
			if(crs.getCourse_name().equalsIgnoreCase(course.getCourse_name())
					&& crs.getDepartment_id() == course.getDepartment_id()
					&& crs.getInstitute_id() == course.getInstitute_id()
					&& crs.getType().equalsIgnoreCase(course.getType())) {
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
	
	public String update(int course_id, Course bodyCourse) {
		
		List<Course> courses = course_repository.findAll();
		
		for(Course course : courses) {
			if(course_id != course.getCourse_id()
					&& (course.getCourse_name().equalsIgnoreCase(bodyCourse.getCourse_name())
							&& course.getDepartment_id() == bodyCourse.getDepartment_id()
							&& course.getType().equalsIgnoreCase(bodyCourse.getType())
							&& course.getInstitute_id() == bodyCourse.getInstitute_id())) {
				return null;
			}
		}
	
		for(Course course : courses) {
			if(course.getCourse_id() == course_id) {
				course.setCourse_name(bodyCourse.getCourse_name());
				course.setCourse_code(bodyCourse.getCourse_code());
				course.setCredit_hours(bodyCourse.getCredit_hours());
				course.setType(bodyCourse.getType());
				course.setDepartment_id(bodyCourse.getDepartment_id());
				course_repository.save(course);
				break;
			}
		}
		
		redisUtilityRoot.deleteList(HASH_KEY_COURSE_LIST+bodyCourse.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	public String delete(int course_id) {
		int institute_id= 0;
		List<Course> courses = course_repository.findAll();
		for(Course course : courses) {
			if(course.getCourse_id() == course_id) {
				institute_id = course.getInstitute_id();
				course_repository.deleteById(course_id);
				break;
			}
		}
		redisUtilityRoot.deleteList(HASH_KEY_COURSE_LIST+institute_id);
		return "Deleted successfully.";
	}
	
}
