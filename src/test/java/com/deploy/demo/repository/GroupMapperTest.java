package com.deploy.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.deploy.demo.entity.GroupEntity;

@SpringBootTest
class GroupMapperTest {

	@Autowired
	private GroupMapper groupMapper;

	@Test
	void insert() {
		GroupEntity group = new GroupEntity();
		group.setName("groupname");
		group.setCrDateTime(LocalDateTime.now());
		group.setCrUser("cruser");

		Integer id = groupMapper.insertGroup(group);
		System.out.println(id);
	}

	@Test
	void listGroup() {

		List<GroupEntity> list = groupMapper.listGroup();

		if (list != null) {
			System.out.println("List GroupEntity:");
			list.forEach(group -> System.out.println(group.toString()));
		} else
			System.out.println("Null");
	}

	@Test
	void findByGroupId() {
		int id = 1;
		GroupEntity group = groupMapper.findByGroupId(id);

		if (group != null)
			System.out.println("findByGroupId測試成功:" + group.toString());
		else
			System.out.println("Null");
	}

	@Test
	void updateGroup() {
		int id = 1;
		GroupEntity group = groupMapper.findByGroupId(id);

		if (group != null) {
			group.setName("newname");
			group.setUpDateTime(LocalDateTime.now());
			group.setUpUser("upuser");

			Integer result = groupMapper.updateGroup(group);
			System.out.println(result);
		} else
			System.out.println("更新失敗或無此資料");

	}

	@Test
	void deleteGroup() {
		int id = 1;

		try {
			groupMapper.deleteGroup(id);
			System.out.println("刪除成功");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
