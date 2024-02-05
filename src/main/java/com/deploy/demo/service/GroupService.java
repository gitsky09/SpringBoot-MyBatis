package com.deploy.demo.service;

import java.util.List;

import com.deploy.demo.entity.GroupEntity;

public interface GroupService {

	public int insertGroup(GroupEntity group);

	public List<GroupEntity> listGroup();

	public GroupEntity getGroup(int id);

	public Integer updateGroup(GroupEntity group);

	public void deleteGroup(int id);
}
