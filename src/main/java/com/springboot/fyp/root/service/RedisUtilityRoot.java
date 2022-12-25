package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.springboot.fyp.root.models.Institute_type;

@Service
public class RedisUtilityRoot {
	
	public static final String HASH_KEY_INSTITUTE_TYPE_LISTS = "InstituteTypes";
	
	@Qualifier("redisTemplate")
	@Autowired
	private RedisTemplate<String, Object> template;
	
	public void saveTypes(List<Institute_type> types){
		template.opsForHash().put(HASH_KEY_INSTITUTE_TYPE_LISTS, HASH_KEY_INSTITUTE_TYPE_LISTS, types);
		template.expire(HASH_KEY_INSTITUTE_TYPE_LISTS, 24, TimeUnit.HOURS);
	}
	
	@SuppressWarnings("unchecked")
	public List<Institute_type> getTypes(){
		var list = template.opsForHash().get(HASH_KEY_INSTITUTE_TYPE_LISTS, HASH_KEY_INSTITUTE_TYPE_LISTS);
		if(list != null) {
			return (List<Institute_type>) list;
		}
		return new ArrayList<Institute_type>();
	}
	
}
