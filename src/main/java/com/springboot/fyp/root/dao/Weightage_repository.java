package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Weightage;

@Repository
public interface Weightage_repository extends MongoRepository<Weightage, Integer> {

}
