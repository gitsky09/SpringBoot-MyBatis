package com.deploy.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.deploy.demo.entity.UserEntity;


public interface UserMapper {

	@Insert("INSERT INTO deploy_users( NAME, ACCOUNT, PASSWORD, SALT, PHONE, CREATE_TIME, CREATE_USER )VALUES( #{name}, #{account}, #{password}, #{salt}, #{phone}, #{crDateTime}, #{crUser})")
	public int save(UserEntity user);
	
	@Select("SELECT deploy_users.name,deploy_users.account,deploy_users.phone FROM deploy_users")
	public List<UserEntity> listUser();

	@Select("SELECT * FROM deploy_users WHERE ID = #{id}")
	public UserEntity findByUserId(int id);

	@Update("UPDATE deploy_users SET NAME = #{name},ACCOUNT = #{account},PASSWORD = #{password},PHONE = #{phone},UPDATE_TIME = #{upDateTime}, UPDATE_USER = #{upUser} WHERE ID = #{id}")
	public Integer updateUser(UserEntity user);
	
	@Select("SELECT * FROM deploy_users WHERE ACCOUNT = #{account}")
	UserEntity findByAccount(String account);
	
}
