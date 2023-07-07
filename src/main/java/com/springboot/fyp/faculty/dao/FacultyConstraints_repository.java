package com.springboot.fyp.faculty.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.faculty.models.FacultyConstraints;

@Repository
public interface FacultyConstraints_repository extends MongoRepository<Integer, FacultyConstraints> {

}
