package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Resource_occupied;

@Repository
public interface Resource_occupied_repository extends MongoRepository<Resource_occupied, Integer> {

}
