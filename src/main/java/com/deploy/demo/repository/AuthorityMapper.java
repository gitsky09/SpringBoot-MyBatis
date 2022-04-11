package com.deploy.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.deploy.demo.entity.AuthorityEntity;

public interface AuthorityMapper {

	@Insert("INSERT deploy_authority(NAME,CREATE_TIME,CREATE_USER)VALUES(#{name},#{crDateTime},#{crUser})")
	public Integer insertAuthority(AuthorityEntity authority);
	
	@Select("SELECT deploy_authority.name FROM deploy_authority")
	public List<AuthorityEntity> listAuthority();

	@Select("SELECT * FROM deploy_authority WHERE ID = #{id}")
	public AuthorityEntity findByAuthorityId(int id);

	@Update("UPDATE deploy_authority SET NAME = #{name},UPDATE_TIME = #{upDateTime}, UPDATE_USER = #{upUser} WHERE ID = #{id}")
	public int updateAuthority(AuthorityEntity authority);

	@Delete("DELETE FROM deploy_authority WHERE	ID = #{id}")
	public void deleteAuthority(int id);
}
