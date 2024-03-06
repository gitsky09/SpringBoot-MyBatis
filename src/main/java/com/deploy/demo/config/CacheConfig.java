package com.deploy.demo.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deploy.demo.catche.CustomKeyGenerator;

@Configuration
public class CacheConfig {

	@Bean(name = "myKeyGenerator")
	public KeyGenerator myKeyGenerator() {
	    return new CustomKeyGenerator();
	}

}
