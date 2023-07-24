package com.springboot.fyp.root.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fyp.root.models.OfferCourse;

@Repository
public interface OfferCourse_repository extends MongoRepository<OfferCourse, Integer> {

}
