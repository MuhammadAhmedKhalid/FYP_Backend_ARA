package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Institute_type;

@Repository
public interface Institute_type_repository extends MongoRepository<Institute_type, Integer> {

}
