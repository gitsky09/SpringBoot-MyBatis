package com.deploy.demo.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.deploy.demo.entity.AuthorityEntity;
import com.deploy.demo.repository.AuthorityMapper;

@SpringBootTest
public class RedisTest {

	@Autowired
	AuthorityMapper authorityMapper;

	/*
	 * 改寫後的序列化器
	 */
	@Autowired
	RedisTemplate<Object, AuthorityEntity> authauthRedisTemplate;

	/*
	 * 操作字串
	 */
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	/*
	 * 操作物件
	 */
	@Autowired
	RedisTemplate redisTemplate;

	@Test
	public void test() {
//		stringRedisTemplate.opsForValue().append("msg", "hi,Zac");
//		String str = stringRedisTemplate.opsForValue().get("msg");
//		System.out.println(str);

//		stringRedisTemplate.opsForList().leftPush("redidList", "z");
//		stringRedisTemplate.opsForList().leftPush("redidList", "a");
//		stringRedisTemplate.opsForList().leftPush("redidList", "c");
	}

	@Test
	public void saveObj() {
		AuthorityEntity authEntity = authorityMapper.findByAuthorityId(2);
		// 改寫序列化工具
		authauthRedisTemplate.opsForValue().set("authKey", authEntity);
	}

}
