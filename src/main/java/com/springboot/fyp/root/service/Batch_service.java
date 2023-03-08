package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Batch_repository;
import com.springboot.fyp.root.models.Batch;

@Service
public class Batch_service {
	
	@Autowired
	Batch_repository batch_repository;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_BATCHES_LIST = "BatchesList";
	
	public String insert(Batch batch){
		for(Batch btch: batch_repository.findAll()) {
			if(btch.getBatchType().equals(batch.getBatchType()) 
					&& btch.getBatchYear() == batch.getBatchYear()
					&& btch.getDepartment_id() == batch.getDepartment_id()) {
				return null;
			}
		}
		
		batch.setBatchId(sequenceGeneratorService.getSequenceNumber(batch.SEQUENCE_NAME));
		batch_repository.insert(batch);
		redisUtilityRoot.deleteList(HASH_KEY_BATCHES_LIST+batch.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Batch> getAll(int institute_id){
		if(redisUtilityRoot.getList(HASH_KEY_BATCHES_LIST+institute_id).size() > 0) {
			return redisUtilityRoot.getList(HASH_KEY_BATCHES_LIST+institute_id);
		}else {
			if(batch_repository.findAll().isEmpty()) {
				return null;
			}else {
				List<Batch> batchesList = new ArrayList<>();
				for(Batch batch : batch_repository.findAll()) {
					if(batch.getInstitute_id() == institute_id) {
						batchesList.add(batch);
					}
				}
				redisUtilityRoot.saveList(batchesList, HASH_KEY_BATCHES_LIST+institute_id);
				return batchesList;
			}
		}
	}
	
}
