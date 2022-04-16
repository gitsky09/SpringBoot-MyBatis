package com.deploy.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.deploy.demo.entity.UserEntity;
import com.deploy.demo.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	final UserMapper userMapper;

	public Integer save(UserEntity user) {
		String salt= UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		user.setSalt(salt);
		return userMapper.save(user);
	}

	public List<UserEntity> listUser() {
		return userMapper.listUser();
	}

	public UserEntity getUser(int id) {
		return userMapper.findByUserId(id);
	}

	public Integer updateUser(UserEntity user) {
		return userMapper.updateUser(user);
	}
	
	public UserEntity findByAccount(String account) {
		return userMapper.findByAccount(account);
	}

}
