package com.springboot.fyp.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.AllocateFaculty_repository;
import com.springboot.fyp.root.dao.OfferCourse_repository;
import com.springboot.fyp.root.models.AllocateFaculty;
import com.springboot.fyp.root.models.OfferCourse;

@Service
public class AllocateFaculty_service {
	
	@Autowired
	AllocateFaculty_repository allocateFaculty_repository;
	
	@Autowired
	OfferCourse_repository offerCourse_repository;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_ALLOCATED_FACULTY_LIST = "AllocatedFaculty";
	public static final String HASH_KEY_OFFERED_COURSES_LIST = "OfferedCourses";
	
	public String add(AllocateFaculty allocateFaculty) {
		
		allocateFaculty.setAllocateFacultyId(sequenceGeneratorService.getSequenceNumber(allocateFaculty.SEQUENCE_NAME));
		allocateFaculty_repository.insert(allocateFaculty);
		
		for(OfferCourse offerCourse : offerCourse_repository.findAll()) {
			if(offerCourse.getOfferCourseId() == allocateFaculty.getOfferCourseId()) {
				offerCourse.setAllocated(true);
				redisUtilityRoot.deleteList(HASH_KEY_OFFERED_COURSES_LIST+offerCourse.getInstitute_id());
				break;
			}
		}
		
		redisUtilityRoot.deleteList(HASH_KEY_ALLOCATED_FACULTY_LIST+allocateFaculty.getInstitute_id());
		return "Operation performed successfully.";
	}
	
}
