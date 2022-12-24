package com.springboot.fyp.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.fyp.admin.dao.Department_repository;
import com.springboot.fyp.admin.models.Department;

@Service
public class Department_service {
	
	@Autowired
	Department_repository department_repository;
	
	public ResponseEntity<String> insert(Department department){
		department_repository.insert(department);
		return ResponseEntity.ok("Operation performed successfully.");
	}
	
	public ResponseEntity<List<Department>> getAll(){
		return ResponseEntity.ok(department_repository.findAll());
	}
	
}
