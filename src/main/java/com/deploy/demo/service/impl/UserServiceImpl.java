package com.deploy.demo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.deploy.demo.entity.Permission;
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
	 * {使用自定義的key來做cache}
	 * {定義何種情況用緩存}
	 * @return List<UserEntity> {cacheName/value}快取組件的名字
	 *         key:快取資料使用的key，可以指定。預設是使用方法參數的值
	 * 
	 */
	@Cacheable(cacheNames = "userList" , keyGenerator = "myKeyGenerator" , unless = "#result == null")
	@Override
	public List<UserEntity> listUser() {
		return userMapper.listUser();
	}

	@Override
	public UserEntity getUser(int id) {
		return userMapper.findByUserId(id);
	}

	/**
	 * {更新資料後將結果更新快取，key使用#user.id與#result.id都能達到效果}
	 */
	@CachePut(value = {"emp"} , key="#user.id")
	@Override
	public Integer updateUser(UserEntity user) {
		return userMapper.updateUser(user);
	}

	/**
	 * {特定帳號不做快取}
	 */
	@Cacheable(value = {"emp"} , condition = "#a1 == chad@mail.com" ,unless = "#a1 == zac@mail.com")
	@Override
	public UserEntity findByAccount(String account) {
		return userMapper.findByAccount(account);
	}

	@Override
	public List<Permission> findPermissionByAccount(String account) {
		return userMapper.findPermissionByAccount(account);
	}

}
