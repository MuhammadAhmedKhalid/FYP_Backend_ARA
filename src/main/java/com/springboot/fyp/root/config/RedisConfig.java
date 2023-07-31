package com.springboot.fyp.root.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("containers-us-west-145.railway.app");
        configuration.setPort(6625);
        configuration.setPassword("jsO0HaxW880EZAaJ5e8w");
        return new JedisConnectionFactory(configuration);
    }
	
	@Bean
	RedisTemplate<String, Object> redisTemplate(){
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory());
		
		template.setKeySerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		template.setHashKeySerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;
		
	}
	
}
