package com.springboot.fyp.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.OfferCourse_repository;
import com.springboot.fyp.root.models.OfferCourse;

@Service
public class OfferCourse_service {

	@Autowired
	OfferCourse_repository offerCourse_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_OFFERED_COURSES_LIST = "OfferedCourses";
	
	public String add(OfferCourse offerCourse) {
		
		for(OfferCourse courseOffer : offerCourse_repository.findAll()) {
			if(courseOffer.getBatchId() == offerCourse.getBatchId() &&
					courseOffer.getCourse_id() == offerCourse.getCourse_id() &&
					courseOffer.getDepartment_id() == offerCourse.getDepartment_id() &&
					courseOffer.getInstitute_id() == offerCourse.getInstitute_id()) {
				return null;
			}
		}
		
		offerCourse.setOfferCourseId(sequenceGeneratorService.getSequenceNumber(offerCourse.SEQUENCE_NAME));
		offerCourse_repository.insert(offerCourse);
		redisUtilityRoot.deleteList(HASH_KEY_OFFERED_COURSES_LIST+offerCourse.getInstitute_id());
		return "Operation performed successfully.";
	}
	
}
