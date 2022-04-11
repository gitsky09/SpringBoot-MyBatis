package com.deploy.demo.repository;

import java.util.List;

import com.deploy.demo.entity.UserEntity;


public interface UserMapper {

	public int save(UserEntity user);
	
	public List<UserEntity> listUser();

	public UserEntity findByUserId(int id);

	public Integer updateUser(UserEntity user);
	
	public UserEntity findByAccount(String account);

}
