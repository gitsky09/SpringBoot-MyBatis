package com.deploy.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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

	@Cacheable(value = {"authorityList"})
	@Override
	public AuthorityEntity getAuthority(int id) {
		return authorityMapper.findByAuthorityId(id);
	}

	/**
	 * {更新資料後將結果更新快取，key使用#authority.id與#result.id都能達到效果}
	 */
	@CachePut(value = {"authorityList"} , key = "#result.id")
	@Override
	public Integer updateAuthority(AuthorityEntity authority) {
		return authorityMapper.updateAuthority(authority);
	}

	/**
	 * {清除快取內的資料}
	 * {依照key清除快取內的資料}
	 */
	@CacheEvict(value = {"authorityList"} , allEntries = true)
//	@Caching(cacheable=@Cacheable(value = {"authority"})
//	,put=@CachePut(value= {"authority"})
//	,evict=@CacheEvict(value= {"authority"})
//)
	@Override
	public void deleteAuthority(int id) {
		authorityMapper.deleteAuthority(id);
	}
}