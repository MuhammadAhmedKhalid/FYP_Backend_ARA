package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.AssignedCourse;

@Repository
public interface AssignedCourse_repository extends MongoRepository<AssignedCourse, Integer> {

}
