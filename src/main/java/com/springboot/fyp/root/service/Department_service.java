package com.springboot.fyp.root.service;

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
	
	public String insert(Department department){
		department.setDepartment_id(sequenceGeneratorService.getSequenceNumber(department.SEQUENCE_NAME));
		department_repository.insert(department);
		return "Operation performed successfully.";
	}
	
	public List<Department> getAll(){
		return department_repository.findAll();
	}
	
}
