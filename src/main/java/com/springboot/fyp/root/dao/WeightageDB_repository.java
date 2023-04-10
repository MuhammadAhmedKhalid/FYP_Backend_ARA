package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.WeightageDB;

@Repository
public interface WeightageDB_repository extends MongoRepository<WeightageDB, Integer> {

}
