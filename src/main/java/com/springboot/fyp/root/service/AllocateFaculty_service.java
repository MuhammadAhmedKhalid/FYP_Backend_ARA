package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
				offerCourse_repository.save(offerCourse);
				redisUtilityRoot.deleteList(HASH_KEY_OFFERED_COURSES_LIST+offerCourse.getInstitute_id());
				break;
			}
		}
		
		redisUtilityRoot.deleteList(HASH_KEY_ALLOCATED_FACULTY_LIST+allocateFaculty.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<AllocateFaculty> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_ALLOCATED_FACULTY_LIST+institute_id).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_ALLOCATED_FACULTY_LIST+institute_id);
		} else {
			if(allocateFaculty_repository.findAll().isEmpty()) {
				return null;
			} else {
				List<AllocateFaculty> allocateFaculties = new ArrayList<>();
				for(AllocateFaculty allocateFaculty : allocateFaculty_repository.findAll()) {
					if(allocateFaculty.getInstitute_id() == institute_id) {
						allocateFaculties.add(allocateFaculty);
					}
				}
				redisUtilityRoot.saveList(allocateFaculties, HASH_KEY_ALLOCATED_FACULTY_LIST+institute_id);
				return allocateFaculties;
			}
		}
		
	}
	
	public String update(int allocateFacultyId, AllocateFaculty allocate) {
		List<AllocateFaculty> allocatedFaculty = allocateFaculty_repository.findAll();
		
		for(AllocateFaculty allocateFaculty : allocatedFaculty) {
			if(allocateFacultyId == allocateFaculty.getAllocateFacultyId()) {
				Optional<OfferCourse> offerCourse = offerCourse_repository.findById(allocateFaculty.getOfferCourseId());
				if(!offerCourse.get().isAddedInTimetable()) {
					
					offerCourse.get().setAllocated(false);
					offerCourse_repository.save(offerCourse.get());
					
					allocateFaculty.setFaculty_id(allocate.getFaculty_id());
					allocateFaculty.setOfferCourseId(allocate.getOfferCourseId());
					
					allocateFaculty_repository.save(allocateFaculty);
					redisUtilityRoot.deleteList(HASH_KEY_ALLOCATED_FACULTY_LIST+allocateFaculty.getInstitute_id());
					
					Optional<OfferCourse> updOfferCourse = 
							offerCourse_repository.findById(allocateFaculty.getOfferCourseId());
					updOfferCourse.get().setAllocated(true);
					offerCourse_repository.save(updOfferCourse.get());
					redisUtilityRoot.deleteList(HASH_KEY_OFFERED_COURSES_LIST+offerCourse.get().getInstitute_id());

				} else {
					return "Can't delete.";
				}
				break;
			}
		}
		
		redisUtilityRoot.deleteList(HASH_KEY_ALLOCATED_FACULTY_LIST+allocate.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	public String delete(int allocateFacultyId) {
		for(AllocateFaculty allocateFaculty : allocateFaculty_repository.findAll()) {
			if(allocateFaculty.getAllocateFacultyId() == allocateFacultyId) {
				Optional<OfferCourse> offerCourse = offerCourse_repository.findById(allocateFaculty.getOfferCourseId());
				if(!offerCourse.get().isAddedInTimetable()) {
					
					allocateFaculty_repository.delete(allocateFaculty);
					redisUtilityRoot.deleteList(HASH_KEY_ALLOCATED_FACULTY_LIST+allocateFaculty.getInstitute_id());
					
					offerCourse.get().setAllocated(false);
					offerCourse_repository.save(offerCourse.get());
					redisUtilityRoot.deleteList(HASH_KEY_OFFERED_COURSES_LIST+offerCourse.get().getInstitute_id());
					
					return "Deleted successfully.";
				} else {
					return "Can't delete.";
				}
				
			}
		}
		return null;
	}
	
}
