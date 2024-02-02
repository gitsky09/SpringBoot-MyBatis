package com.deploy.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.deploy.demo.entity.GroupEntity;

public interface GroupMapper {

	@Insert("INSERT deploy_groups(NAME,CREATE_TIME,CREATE_USER) VALUES(#{name},#{crDateTime},#{crUser})")
	public int insertGroup(GroupEntity group);

	@Select("SELECT * FROM deploy_groups")
	public List<GroupEntity> listGroup();

	@Select("SELECT * FROM deploy_groups WHERE ID = #{id}")
	public GroupEntity findByGroupId(int id);

	@Update("UPDATE deploy_groups SET NAME = #{name},UPDATE_TIME = #{upDateTime}, UPDATE_USER = #{upUser} WHERE ID = #{id}")
	public int updateGroup(GroupEntity group);

	@Delete("DELETE FROM deploy_groups WHERE ID = #{id}")
	public void deleteGroup(int id);
	
}
