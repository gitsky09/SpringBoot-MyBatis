package com.deploy.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.deploy.demo.entity.AuthorityEntity;
import com.deploy.demo.repository.AuthorityMapper;
import com.deploy.demo.service.AuthorityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	final AuthorityMapper authorityMapper;

	@Override
	public Integer insertAuthority(AuthorityEntity authority) {
		return authorityMapper.insertAuthority(authority);
	}

	@Cacheable(cacheNames="authorityList")
	@Override
	public List<AuthorityEntity> listAuthority() {
		return authorityMapper.listAuthority();
	}

	@Override
	public AuthorityEntity getAuthority(int id) {
		return authorityMapper.findByAuthorityId(id);
	}

	@Override
	public Integer updateAuthority(AuthorityEntity authority) {
		return authorityMapper.updateAuthority(authority);
	}

	@Override
	public void deleteAuthority(int id) {
		authorityMapper.deleteAuthority(id);
	}
}