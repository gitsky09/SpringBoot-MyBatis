package com.deploy.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deploy.demo.entity.AuthorityEntity;
import com.deploy.demo.enums.HttpState;
import com.deploy.demo.service.AuthorityService;
import com.deploy.demo.utils.JsonResult;

@RestController
@RequestMapping(value = "/api/authorityid")
public class AuthorityRestController {
	
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public JsonResult<List<AuthorityEntity>> list() {
		
		return new JsonResult<List<AuthorityEntity>>(HttpState.SUCCESS.getState(), authorityService.listAuthority());
	}

}
