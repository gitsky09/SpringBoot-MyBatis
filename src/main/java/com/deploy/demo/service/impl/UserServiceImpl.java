package com.deploy.demo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.deploy.demo.entity.UserEntity;
import com.deploy.demo.repository.UserMapper;
import com.deploy.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	final UserMapper userMapper;

	@Override
	public Integer save(UserEntity user) {
		String salt = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		user.setSalt(salt);
		return userMapper.save(user);
	}

	/**
	 * {將方法的運行結果放入快取，以後再請求相同的資料，直接從快取提取，不用呼叫方法}
	 * 
	 * @return List<UserEntity> {cacheName/value}快取組件的名字
	 *         key:快取資料使用的key，可以指定。預設是使用方法參數的值
	 * 
	 */
	@Cacheable(cacheNames = "userList")
	@Override
	public List<UserEntity> listUser() {
		return userMapper.listUser();
	}

	@Override
	public UserEntity getUser(int id) {
		return userMapper.findByUserId(id);
	}

	@Override
	public Integer updateUser(UserEntity user) {
		return userMapper.updateUser(user);
	}

	@Override
	public UserEntity findByAccount(String account) {
		return userMapper.findByAccount(account);
	}

}
