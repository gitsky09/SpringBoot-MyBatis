package com.deploy.demo.service;

import java.util.List;

import com.deploy.demo.entity.AuthorityEntity;

public interface AuthorityService {

	public Integer insertAuthority(AuthorityEntity authorityEntity);

	public List<AuthorityEntity> listAuthority();

	public AuthorityEntity getAuthority(int id);

	public Integer updateAuthority(AuthorityEntity authorityEntity);

	public void deleteAuthority(int id);
}
