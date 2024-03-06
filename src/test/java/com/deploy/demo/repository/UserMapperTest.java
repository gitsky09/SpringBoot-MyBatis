package com.deploy.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.deploy.demo.entity.UserEntity;

@SpringBootTest
class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Test
	void save() {
		UserEntity user = new UserEntity();
		user.setName("username");
		user.setAccount("test");
		user.setPassword(encoder.encode("123"));
		
		String salt= UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		user.setSalt(salt);
		user.setPhone("3345678");
		user.setCrDateTime(LocalDateTime.now());
		user.setCrUser("cruser");

		Integer id = userMapper.save(user);
		System.out.println("save():" + id);
	}

	@Test
	void listUser() {
		List<UserEntity> list = userMapper.listUser();

		if (list != null) {
			System.out.println("listUser():");
			list.forEach(user -> System.out.println(user.toString()));
		} else
			System.out.println("Null");
	}

	@Test
	void findByUserId() {

		int id = 1;
		UserEntity user = userMapper.findByUserId(id);

		if (user != null)
			System.out.println("findByUserId():" + user.toString());
		else
			System.out.println("Null");

	}

	@Test
	void updateUser() {

		int id = 7;
		UserEntity currentUser = userMapper.findByUserId(id);

		if (currentUser != null) {
			currentUser.setName("zac");
//			currentUser.setAccount("zac@mail.com");
			currentUser.setPassword(encoder.encode("Test1234"));
			currentUser.setUpDateTime(LocalDateTime.now());
			currentUser.setUpUser("zac");

			Integer result = userMapper.updateUser(currentUser);
			System.out.println("updateUser():" + result);
		} else
			System.out.println("更新失敗或無此資料");

	}
	
	@Test
	void selectAccount() {
		
		Optional<UserEntity> currentUser =  userMapper.findByAccount("test");
		if(!currentUser.isPresent())
			System.out.println("無此帳號");
		else
			System.out.println("selectAccount():" + currentUser.toString());
	}

}
