package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	public List<OfferCourse> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_OFFERED_COURSES_LIST+institute_id).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_OFFERED_COURSES_LIST+institute_id);
		} else {
			if(offerCourse_repository.findAll().isEmpty()) {
				return null;
			} else {
				List<OfferCourse> offeredCourses = new ArrayList<>();
				for(OfferCourse offeredCourse : offerCourse_repository.findAll()) {
					if(offeredCourse.getInstitute_id() == institute_id) {
						offeredCourses.add(offeredCourse);
					}
				}
				redisUtilityRoot.saveList(offeredCourses, HASH_KEY_OFFERED_COURSES_LIST+institute_id);
				return offeredCourses;
			}
		}
	}
	
}
