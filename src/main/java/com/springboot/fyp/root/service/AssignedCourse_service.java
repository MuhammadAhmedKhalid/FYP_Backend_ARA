package com.springboot.fyp.root.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.AssignedCourse_repository;
import com.springboot.fyp.root.dao.Institute_repository;
import com.springboot.fyp.root.models.AssignedCourse;
import com.springboot.fyp.root.models.Institute;

@Service
public class AssignedCourse_service {
	
	@Autowired
	AssignedCourse_repository assignCourse_repository;
	
	@Autowired
	Institute_repository institute_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_ASSIGNED_COURSE_LIST = "AssignedCourseList";
	
	public class DateFetcher {
	    public static List<LocalDate> fetchDatesForDayOfMonth(int year, int month, DayOfWeek dayOfWeek) {
	        LocalDate start = LocalDate.of(year, month, 1);
	        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
	        List<LocalDate> dates = new ArrayList<>();
	        while (start.isBefore(end) || start.equals(end)) {
	            if (start.getDayOfWeek() == dayOfWeek) {
	                dates.add(start);
	            }
	            start = start.plusDays(1);
	        }
	        return dates;
	    }
	}
	
	@SuppressWarnings("unused")
	public String insert(AssignedCourse assignedCourse){
		assignedCourse.setAssignedCourseId(sequenceGeneratorService.getSequenceNumber(assignedCourse.SEQUENCE_NAME));
		
		String startingMonth = null;
		String endingMonth = null;
		String day = assignedCourse.getDay();
		for(Institute institute : institute_repository.findAll()) {
			if(institute.getInstitute_id() == assignedCourse.getInstitute_id()) {
				if(assignedCourse.getSemesterType().equals("SPRING")) {
					startingMonth = institute.getSpringStartMonth();
					endingMonth = institute.getSpringEndMonth();
				}else {
					startingMonth = institute.getFallStartMonth();
					endingMonth = institute.getFallEndMonth();
				}
			}
		}
		System.out.println(startingMonth);
		System.out.println(endingMonth);
//		List<LocalDate> dates = DateFetcher.fetchDatesForDayOfMonth(2023, 3, DayOfWeek.FRIDAY);
//		for (LocalDate date : dates) {
//		    System.out.println(date);
//		}
		
//		assignCourse_repository.insert(assignedCourse);
//		redisUtilityRoot.deleteList(HASH_KEY_ASSIGNED_COURSE_LIST+assignedCourse.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<AssignedCourse> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_ASSIGNED_COURSE_LIST+institute_id).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_ASSIGNED_COURSE_LIST+institute_id);
		}else {
			if(assignCourse_repository.findAll().isEmpty()) {
				return null;
			}else {
				List<AssignedCourse> assignedCourses = new ArrayList<>();
				for(AssignedCourse course : assignCourse_repository.findAll()) {
					if(course.getInstitute_id() == institute_id) {
						assignedCourses.add(course);
					}
				}
				redisUtilityRoot.saveList(assignedCourses, HASH_KEY_ASSIGNED_COURSE_LIST+institute_id);
				return assignedCourses;
			}
		}
	}
	
}
