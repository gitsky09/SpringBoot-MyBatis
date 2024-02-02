package com.deploy.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.deploy.demo.entity.GroupEntity;
import com.deploy.demo.repository.GroupMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService {

	final GroupMapper groupMapper;

	@PostMapping("/group")
	public int insertGroup(GroupEntity group) {
		return groupMapper.insertGroup(group);
	}

	@GetMapping("/group")
	public List<GroupEntity> listGroup() {
		return groupMapper.listGroup();
	}

	@GetMapping("/group")
	public GroupEntity getGroup(int id) {
		return groupMapper.findByGroupId(id);
	}

	@PutMapping("/group")
	public Integer updateGroup(GroupEntity group) {
		return groupMapper.updateGroup(group);
	}

	@DeleteMapping("/group")
	public void deleteGroup(int id) {
		groupMapper.deleteGroup(id);
	}

}
