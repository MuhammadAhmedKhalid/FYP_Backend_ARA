package com.springboot.fyp.root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.WeightageDB_repository;
import com.springboot.fyp.root.models.Weightage;
import com.springboot.fyp.root.models.WeightageDB;

@Service
public class Weightage_service {
	
	@Autowired
	WeightageDB_repository weightageDB_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_WEIGHTAGE_LIST = "WeightageList";
	
	public String insert(Weightage weightage) {
		for(int i=0; i<weightage.getAssignedCourse().size(); i++) {
			WeightageDB weightageDB = new WeightageDB();
			weightageDB.setWeightageId(sequenceGeneratorService.getSequenceNumber(weightageDB.SEQUENCE_NAME));
			weightageDB.setAssignedCourse(weightage.getAssignedCourse().get(i));
			weightageDB.setInstitute_id(weightage.getInstitute_id());
			weightageDB.setJaccardResults(weightage.getJaccardResults().get(i));
			weightageDB_repository.insert(weightageDB);
			redisUtilityRoot.deleteList(HASH_KEY_WEIGHTAGE_LIST+weightage.getInstitute_id());
		}
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<WeightageDB> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_WEIGHTAGE_LIST).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_WEIGHTAGE_LIST);
		}else {
			if(weightageDB_repository.findAll().isEmpty()) {
				return null;
			}
			redisUtilityRoot.saveList(weightageDB_repository.findAll(), HASH_KEY_WEIGHTAGE_LIST);
			return redisUtilityRoot.getList(HASH_KEY_WEIGHTAGE_LIST);
		}
	}
	
	public String update(int weightageId) {
		int institute_id= 0;
		for(WeightageDB weightage : weightageDB_repository.findAll()) {
			if(weightage.getWeightageId() == weightageId) {
				weightage.setSelected(true);
				institute_id = weightage.getInstitute_id();
				weightageDB_repository.save(weightage);
				break;
			}
		}
		redisUtilityRoot.deleteList(HASH_KEY_WEIGHTAGE_LIST+institute_id);
		return "Operation performed successfully.";
	}
	
}
