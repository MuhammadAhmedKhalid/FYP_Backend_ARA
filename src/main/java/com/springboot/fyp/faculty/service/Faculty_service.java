package com.springboot.fyp.faculty.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.admin.service.Admin_service;
import com.springboot.fyp.faculty.dao.Faculty_repositiory;
import com.springboot.fyp.faculty.models.Faculty;
import com.springboot.fyp.root.dao.User_repository;
import com.springboot.fyp.root.service.RedisUtilityRoot;
import com.springboot.fyp.root.service.SequenceGeneratorService;

@Service
public class Faculty_service {
	
	final String secretKey = "3t6w9y$B&E)H@McQ";
	
	@Autowired
	Faculty_repositiory faculty_repositiory;
	
	@Autowired
	User_repository user_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	Admin_service admin_service;
	
	public static final String HASH_KEY_FACULTY_LIST = "FacultyList";
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public String create(Faculty faculty){
		Faculty checkFaculty = faculty_repositiory.findByOfficialEmailAddress(faculty.getOfficialEmailAddress()); 
		if(checkFaculty != null) {
			return null;
		}
		admin_service.create(faculty.getUser(), true);
		faculty.setFaculty_id(sequenceGeneratorService.getSequenceNumber(faculty.SEQUENCE_NAME));
		faculty_repositiory.insert(faculty);
		redisUtilityRoot.deleteList(HASH_KEY_FACULTY_LIST+faculty.getInstitute_id());
		return "Operation performed successfully.";
	}

	@SuppressWarnings("unchecked")
	public List<Faculty> getAll(int institute_id) {
		if(redisUtilityRoot.getList(HASH_KEY_FACULTY_LIST+institute_id).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_FACULTY_LIST+institute_id);
		}else {
			if(faculty_repositiory.findAll().isEmpty()) {
				return null;
			}
			List<Faculty> faculty_list = new ArrayList<>();
			for(Faculty faculty : faculty_repositiory.findAll()) {
				if( faculty.getInstitute_id() == institute_id) {
					faculty_list.add(faculty);
				}
			}
			redisUtilityRoot.saveList(faculty_list, HASH_KEY_FACULTY_LIST+institute_id);
			return redisUtilityRoot.getList(HASH_KEY_FACULTY_LIST+institute_id);
		}
	}
	
	public String update(int faculty_id, Faculty facultyObj) {
		int institute_id= 0;
		List<Faculty> facultyList = faculty_repositiory.findAll();
		for(Faculty faculty : facultyList) {
			if(faculty.getFaculty_id() == faculty_id) {
				faculty.setName(facultyObj.getName());
				faculty.setPhone_number(facultyObj.getPhone_number());
				faculty.setDesignation(facultyObj.getDesignation());
				institute_id = faculty.getInstitute_id();
				faculty_repositiory.save(faculty);
				break;
			}
		}
		redisUtilityRoot.deleteList(HASH_KEY_FACULTY_LIST+institute_id);
		return "Operation performed successfully.";
	}
	
}
