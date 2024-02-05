package com.deploy.demo.service;

import java.util.List;

import com.deploy.demo.entity.UserEntity;

public interface UserService {

	public Integer save(UserEntity user);

	public List<UserEntity> listUser();

	public UserEntity getUser(int id);

	public Integer updateUser(UserEntity user);

	public UserEntity findByAccount(String account);

}
