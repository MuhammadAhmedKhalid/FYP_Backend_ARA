package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Department_repository;
import com.springboot.fyp.root.models.Department;

@Service
public class Department_service {
	
	@Autowired
	Department_repository department_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_DEPARTMENTS_LIST = "DepartmentsList";
	
	public String insert(Department department){
		
		for(Department dep: department_repository.findAll()) {
			if(dep.getDepartment_name().equals(department.getDepartment_name())) {
				return null;
			}
		}
		
		department.setDepartment_id(sequenceGeneratorService.getSequenceNumber(department.SEQUENCE_NAME));
		department_repository.insert(department);
		redisUtilityRoot.deleteList(HASH_KEY_DEPARTMENTS_LIST+department.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_DEPARTMENTS_LIST+institute_id).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_DEPARTMENTS_LIST+institute_id);
		}else {
			if(department_repository.findAll().isEmpty()) {
				return null;
			}else {
				List<Department> department_lst = new ArrayList<>();
				for(Department department : department_repository.findAll()) {
					if(department.getInstitute_id() == institute_id) {
						department_lst.add(department);
					}
				}
				redisUtilityRoot.saveList(department_lst, HASH_KEY_DEPARTMENTS_LIST+institute_id);
				return department_lst;
			}
		}
	}
	
}
