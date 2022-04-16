package com.deploy.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deploy.demo.entity.GroupEntity;
import com.deploy.demo.enums.HttpState;
import com.deploy.demo.service.GroupService;
import com.deploy.demo.utils.JsonResult;

@RestController
@RequestMapping(value = "/api/groupid")
public class GroupRestController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public JsonResult<List<GroupEntity>> list() {
		
		return new JsonResult<List<GroupEntity>>(HttpState.SUCCESS.getState(), groupService.listGroup());
	}

}
