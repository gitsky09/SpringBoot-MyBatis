package com.deploy.demo.repository;

import java.util.List;

import com.deploy.demo.entity.AuthorityEntity;

public interface AuthorityMapper {

	public Integer insertAuthority(AuthorityEntity authority);
	
	public List<AuthorityEntity> listAuthority();

	public AuthorityEntity findByAuthorityId(int id);

	public int updateAuthority(AuthorityEntity authority);

	public void deleteAuthority(int id);
}
