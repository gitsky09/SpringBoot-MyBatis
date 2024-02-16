package com.deploy.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.deploy.demo.entity.GroupEntity;
import com.deploy.demo.repository.GroupMapper;
import com.deploy.demo.service.GroupService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

	@Autowired
	final GroupMapper groupMapper;

	@Override
	public int insertGroup(GroupEntity group) {
		return groupMapper.insertGroup(group);
	}

	@Cacheable(cacheNames="groupList")
	@Override
	public List<GroupEntity> listGroup() {
		return groupMapper.listGroup();
	}

	@Cacheable(value = {"groupList"})
	@Override
	public GroupEntity getGroup(int id) {
		return groupMapper.findByGroupId(id);
	}

	/**
	 * {更新資料後將結果更新快取，key使用#group.id與#result.id都能達到效果}
	 */
	@CachePut(value = {"groupList"} , key = "#result.id")
	@Override
	public Integer updateGroup(GroupEntity group) {
		return groupMapper.updateGroup(group);
	}

	/**
	 * {清除快取內的資料}
	 */
	@CacheEvict(value = {"groupList"} , allEntries = true)
//	@Caching(cacheable=@Cacheable(value = {"group"})
//				,put=@CachePut(value= {"group"})
//				,evict=@CacheEvict(value= {"group"})
//			)
	@Override
	public void deleteGroup(int id) {
		groupMapper.deleteGroup(id);
	}

}
