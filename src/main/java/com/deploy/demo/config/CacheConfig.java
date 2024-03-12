package com.deploy.demo.config;

import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.deploy.demo.CustomKeyGenerator;
import com.deploy.demo.entity.AuthorityEntity;

@Configuration
public class CacheConfig {

	@Bean(name = "myKeyGenerator")
	public KeyGenerator myKeyGenerator() {
	    return new CustomKeyGenerator();
	}
	
	@Bean
	public RedisTemplate<Object, AuthorityEntity> authRedisTemplate(
			RedisConnectionFactory redisConnectionFactory)
					throws UnknownHostException {
		RedisTemplate<Object,  AuthorityEntity> template = new RedisTemplate<Object,  AuthorityEntity>();
		template.setConnectionFactory(redisConnectionFactory);
		//添加序列化
		Jackson2JsonRedisSerializer<AuthorityEntity> serializer = new Jackson2JsonRedisSerializer<AuthorityEntity>(AuthorityEntity.class);
		template.setDefaultSerializer(serializer);
		return template;
	}


}
