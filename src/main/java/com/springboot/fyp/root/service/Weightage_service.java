package com.springboot.fyp.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Weightage_repository;
import com.springboot.fyp.root.models.Weightage;

@Service
public class Weightage_service {
	
	@Autowired
	Weightage_repository weightage_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_WEIGHTAGE_LIST = "WeightageList";
	
	public String insert(Weightage weightage) {
		weightage.setWeightageId(sequenceGeneratorService.getSequenceNumber(weightage.SEQUENCE_NAME));
		weightage_repository.insert(weightage);
		redisUtilityRoot.deleteList(HASH_KEY_WEIGHTAGE_LIST+weightage.getInstitute_id());
		return "Operation performed successfully.";
	}
	
}
