package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.dao.Specialization_repository;
import com.springboot.fyp.root.models.Specialization;

@Service
public class Specialization_service {
	
	@Autowired
	Specialization_repository specialization_repository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	RedisUtilityRoot redisUtilityRoot;
	
	public static final String HASH_KEY_SPECIALIZATION_LIST = "SpecializationList";
	
	public String add(Specialization specialization) {
		specialization.setSpecialization_id(sequenceGeneratorService.getSequenceNumber(specialization.SEQUENCE_NAME));
		specialization_repository.insert(specialization);
		redisUtilityRoot.deleteList(HASH_KEY_SPECIALIZATION_LIST+specialization.getInstitute_id());
		return "Operation performed successfully.";
	}
	
	@SuppressWarnings("unchecked")
	public List<Specialization> getAll(int institute_id){
		List<Specialization> specializations = redisUtilityRoot.getList(HASH_KEY_SPECIALIZATION_LIST+institute_id);
		if(specializations.size() > 0) {
			return specializations;
		}else {
			List<Specialization> specialization_lst = specialization_repository.findAll();
			if(specialization_lst.isEmpty()) {
				return null;
			}else {
				List<Specialization> specializations_lst = new ArrayList<>();
				for(Specialization specialization : specialization_lst) {
					if(specialization.getInstitute_id() == institute_id) {
						specializations_lst.add(specialization);
					}
				}
				redisUtilityRoot.saveList(specializations_lst, HASH_KEY_SPECIALIZATION_LIST+institute_id);
				return specializations_lst;
			}
		}
	}
	
}
