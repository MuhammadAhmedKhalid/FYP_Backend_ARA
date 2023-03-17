package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.AssignedCoursesForTable;

@Repository
public interface AssignedCoursesForTable_repository extends MongoRepository<AssignedCoursesForTable, Integer> {

}
