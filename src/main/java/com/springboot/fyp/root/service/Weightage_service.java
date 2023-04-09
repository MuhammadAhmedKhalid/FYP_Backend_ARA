package com.springboot.fyp.root.service;

import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	public List<Weightage> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_WEIGHTAGE_LIST).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_WEIGHTAGE_LIST);
		}else {
			if(weightage_repository.findAll().isEmpty()) {
				return null;
			}
			redisUtilityRoot.saveList(weightage_repository.findAll(), HASH_KEY_WEIGHTAGE_LIST);
			return redisUtilityRoot.getList(HASH_KEY_WEIGHTAGE_LIST);
		}
	}
	
}
