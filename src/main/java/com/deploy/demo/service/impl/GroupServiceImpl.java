package com.deploy.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

	@Override
	public GroupEntity getGroup(int id) {
		return groupMapper.findByGroupId(id);
	}

	@Override
	public Integer updateGroup(GroupEntity group) {
		return groupMapper.updateGroup(group);
	}

	@Override
	public void deleteGroup(int id) {
		groupMapper.deleteGroup(id);
	}

}
