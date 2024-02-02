package com.deploy.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.deploy.demo.entity.AuthorityEntity;

@SpringBootTest
class AuthorityMapperTest {

	@Autowired
	AuthorityMapper authorityMapper;

	@Test
	void insert() {
		AuthorityEntity authority = new AuthorityEntity();
		authority.setName("authorityname");
		authority.setCrDateTime(LocalDateTime.now());
		authority.setCrUser("cruser");

		Integer id = authorityMapper.insertAuthority(authority);
		System.out.println(id);

	}

	@Test
	void listAuthority() {

		List<AuthorityEntity> list = authorityMapper.listAuthority();

		if (list != null) {
			System.out.println("List AuthorityEntity:");
			list.forEach(authority -> System.out.println(authority.toString()));
		} else
			System.out.println("Null");
	}

	@Test
	void findByAuthorityId() {
		int id = 1;
		AuthorityEntity authority = authorityMapper.findByAuthorityId(id);

		if (authority != null)
			System.out.println("findByAuthorityId()測試成功" + authority.toString());
		else
			System.out.println("Null");
	}

	@Test
	void updateAuthority() {

		int id = 1;
		AuthorityEntity authority = authorityMapper.findByAuthorityId(id);

		if (authority != null) {
			authority.setName("newname");
			authority.setUpDateTime(LocalDateTime.now());
			authority.setUpUser("upuser");

			Integer result = authorityMapper.updateAuthority(authority);
			System.out.println(result);
		} else
			System.out.println("更新失敗或無此資料");

	}

	@Test
	void deleteAuthority() {

		int id = 1;
		
		try {
			authorityMapper.deleteAuthority(id);
			System.out.println("刪除成功");
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
}
