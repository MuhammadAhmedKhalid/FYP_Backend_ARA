package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Institute;

@Repository
public interface Institute_repository extends MongoRepository<Institute, Integer> {

}
