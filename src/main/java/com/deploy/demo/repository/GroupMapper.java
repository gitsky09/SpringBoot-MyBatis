package com.deploy.demo.repository;

import java.util.List;

import com.deploy.demo.entity.GroupEntity;

public interface GroupMapper {

	public int insertGroup(GroupEntity group);

	public List<GroupEntity> listGroup();

	public GroupEntity findByGroupId(int id);

	public int updateGroup(GroupEntity group);

	public void deleteGroup(int id);
	
}
