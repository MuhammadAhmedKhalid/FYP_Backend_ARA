package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.AllocateFaculty_repository;
import com.springboot.fyp.root.dao.OfferCourse_repository;
import com.springboot.fyp.root.models.AllocateFaculty;
import com.springboot.fyp.root.models.OfferCourse;

@Service
public class OfferCourse_service {

	@Autowired
	OfferCourse_repository offerCourse_repository;
	
	@Autowired
	AllocateFaculty_repository allocateFaculty_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_OFFERED_COURSES_LIST = "OfferedCourses";
	public static final String HASH_KEY_ALLOCATED_FACULTY_LIST = "AllocatedFaculty";
	
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
	
	public String update(int offerCourseId, OfferCourse offerCourse) {
		
		List<OfferCourse> offeredCourses = offerCourse_repository.findAll();
		
		for(OfferCourse offeredCourse : offeredCourses) {
			if(offerCourseId != offeredCourse.getCourse_id()
					&& offeredCourse.getBatchId() == offerCourse.getBatchId()
					&& offeredCourse.getCourse_id() == offerCourse.getCourse_id()
					&& offeredCourse.getDepartment_id() == offerCourse.getDepartment_id()
					&& offeredCourse.getInstitute_id() == offerCourse.getInstitute_id()) {
				return null;
			}
		}
		
		String operationMsg = "";
		
		for(OfferCourse offeredCourse : offeredCourses) {
			if(offeredCourse.getOfferCourseId() == offerCourseId) {
				if(offeredCourse.isAllocated() == false && offeredCourse.isAddedInTimetable() == false) {
					operationMsg = "Operation performed successfully.";
					edit(offeredCourse, offerCourse);
				} else if (offeredCourse.isAllocated() == true && offeredCourse.isAddedInTimetable() == false) {
					operationMsg = "Operation performed successfully. Reallocate this course to faculty.";
					edit(offeredCourse, offerCourse);
					
					for(AllocateFaculty allocateFaculty : allocateFaculty_repository.findAll()) {
						if(allocateFaculty.getOfferCourseId() == offerCourseId) {
							allocateFaculty_repository.delete(allocateFaculty);
							redisUtilityRoot.deleteList(HASH_KEY_ALLOCATED_FACULTY_LIST+allocateFaculty.getInstitute_id());
							break;
						}
					}
				} else {
					return "Can't update.";
				}
				break;
			}
		}
		
		redisUtilityRoot.deleteList(HASH_KEY_OFFERED_COURSES_LIST+offerCourse.getInstitute_id());
		return operationMsg;
	}
	
	void edit(OfferCourse offeredCourse, OfferCourse offerCourse) {
		offeredCourse.setBatchId(offerCourse.getBatchId());
		offeredCourse.setCourse_id(offerCourse.getCourse_id());
		offeredCourse.setDepartment_id(offerCourse.getDepartment_id());
		offeredCourse.setSemester(offerCourse.getSemester());
		offeredCourse.setAllocated(false);
		offerCourse_repository.save(offeredCourse);
	}
	
	public String delete(int offerCourseId) {
		int institute_id= 0;
		for(OfferCourse offeredCourse : offerCourse_repository.findAll()) {
			if(offerCourseId == offeredCourse.getOfferCourseId()) {
				if(offeredCourse.isAllocated() == false && offeredCourse.isAddedInTimetable() == false) {
					institute_id = offeredCourse.getInstitute_id();
					offerCourse_repository.deleteById(offerCourseId);
					redisUtilityRoot.deleteList(HASH_KEY_OFFERED_COURSES_LIST+institute_id);
					return "Deleted successfully.";
				} else if (offeredCourse.isAllocated() == true && offeredCourse.isAddedInTimetable() == false) {
					institute_id = offeredCourse.getInstitute_id();
					offerCourse_repository.deleteById(offerCourseId);
					
					for(AllocateFaculty allocateFaculty : allocateFaculty_repository.findAll()) {
						if(offeredCourse.getOfferCourseId() == allocateFaculty.getOfferCourseId()) {
							allocateFaculty_repository.delete(allocateFaculty);
							redisUtilityRoot.deleteList(HASH_KEY_ALLOCATED_FACULTY_LIST+institute_id);
							break;
						}
					}
					redisUtilityRoot.deleteList(HASH_KEY_OFFERED_COURSES_LIST+institute_id);
					return "Deleted successfully. Reallocate this course to faculty.";
				} else {
					return "Can't delete.";
				}
			}
		}
		return null;
	}
	
}
