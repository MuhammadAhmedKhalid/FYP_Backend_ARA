package com.springboot.fyp.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		if(response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department already exists with this name.");
		}
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
	
	@PutMapping("/updateDepartment/{department_id}")
	public ResponseEntity<String> updateDepartment(@RequestBody String department_name, 
			@PathVariable("department_id") int department_id){
		String response = department_service.update(department_id, department_name);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department already exists with this name.");
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/deleteDepartment/{department_id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("department_id") int department_id){
		String response = department_service.delete(department_id);
		if(response == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.ok(response);
	}
	
}
