package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.Batch;

@Repository
public interface Batch_repository extends MongoRepository<Batch, Integer> {

}
