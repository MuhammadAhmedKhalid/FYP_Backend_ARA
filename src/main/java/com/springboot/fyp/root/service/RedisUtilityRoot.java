package com.springboot.fyp.root.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisUtilityRoot {
	
	
	@Qualifier("redisTemplate")
	@Autowired
	private RedisTemplate<String, Object> template;
	
	@SuppressWarnings("rawtypes")
	public void saveList(List types, String HASH_KEY_LISTS){
		template.opsForHash().put(HASH_KEY_LISTS, HASH_KEY_LISTS, types);
		template.expire(HASH_KEY_LISTS, 24, TimeUnit.HOURS);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public List getList(String HASH_KEY_LISTS){
		var list = template.opsForHash().get(HASH_KEY_LISTS, HASH_KEY_LISTS);
		if(list != null) {
			return (List) list;
		}
		return new ArrayList<>();
	}
	
	public void deleteList(String HASH_KEY_LISTS) 
	{
		template.opsForHash().delete(HASH_KEY_LISTS, HASH_KEY_LISTS);
	}
	
}
