package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Resource_type;

@Repository
public interface Resource_type_repository extends MongoRepository<Resource_type, Integer>{

}
