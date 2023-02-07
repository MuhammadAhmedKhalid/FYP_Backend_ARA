package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fyp.root.models.Department;
import com.springboot.fyp.root.service.Department_service;

@RestController
public class Department_controller {
	
	@Autowired
	Department_service department_service;
	
	@PostMapping("/add_department")
	public ResponseEntity<String> addDepartment(@RequestBody Department department) {
		String response = department_service.insert(department);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/departments/{institute_id}")
	public ResponseEntity<List<Department>> getDepartments(@PathVariable("institute_id") int institute_id){
		List<Department> departments_lst = department_service.getAll(institute_id);
		if(departments_lst == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(departments_lst);
	}
	
}
