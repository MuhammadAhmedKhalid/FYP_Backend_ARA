package com.springboot.fyp.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.admin.dao.Department_repository;
import com.springboot.fyp.admin.models.Department;

@Service
public class Department_service {
	
	@Autowired
	Department_repository department_repository;
	
	public String insert(Department department){
		department_repository.insert(department);
		return "Operation performed successfully.";
	}
	
	public List<Department> getAll(){
		return department_repository.findAll();
	}
	
}
