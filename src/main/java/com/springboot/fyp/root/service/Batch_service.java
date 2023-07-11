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
			if(btch.getBatchYear() == batch.getBatchYear()
					&& btch.getDepartment_id() == batch.getDepartment_id() 
					&& (btch.getSection() != null && btch.getSection().equalsIgnoreCase(batch.getSection()))) {
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
	
	public String update(int batchId, int department_id, Batch bodyBatch) {
		int institute_id= 0;
		int batchYear = 0;
		List<Batch> batches = batch_repository.findAll();
		
		for(Batch batch : batches) {
			if(batchId == batch.getBatchId()) {
				batchYear = batch.getBatchYear();
			}
		}
		
		for(Batch batch : batches) {
			if(batch.getBatchYear() == bodyBatch.getBatchYear() && batch.getDepartment_id() == department_id) {
				return null;
			} else if(batch.getDepartment_id() == bodyBatch.getDepartment_id() 
					&& batch.getBatchYear() == batchYear) {
				return null;
			} else if(batch.getDepartment_id() == bodyBatch.getDepartment_id() 
					&& batch.getDepartment_id() == department_id && batch.getBatchYear() == bodyBatch.getBatchYear()) {
				return null;
			}
		}
		
		for(Batch batch : batches) {
			if(batch.getBatchId() == batchId) {
				if(bodyBatch.getBatchYear() > 0) {
					batch.setBatchYear(bodyBatch.getBatchYear());
				}
				if(bodyBatch.getDepartment_id() > 0) {
					batch.setDepartment_id(bodyBatch.getDepartment_id());
				}
				institute_id = batch.getInstitute_id();
				batch_repository.save(batch);
				break;
			}
		}
		redisUtilityRoot.deleteList(HASH_KEY_BATCHES_LIST+institute_id);
		return "Operation performed successfully.";
	}
	
	public String delete(int batchId) {
		int institute_id= 0;
		List<Batch> batches = batch_repository.findAll();
		for(Batch batch : batches) {
			if(batch.getBatchId() == batchId) {
				institute_id = batch.getInstitute_id();
				batch_repository.deleteById(batchId);
				break;
			}
		}
		redisUtilityRoot.deleteList(HASH_KEY_BATCHES_LIST+institute_id);
		return "Deleted successfully.";
	}
	
}
