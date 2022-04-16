package com.deploy.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deploy.demo.entity.UserEntity;
import com.deploy.demo.enums.HttpState;
import com.deploy.demo.service.UserService;
import com.deploy.demo.utils.JsonResult;

@RestController
@RequestMapping(value = "/api/account")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public JsonResult<Boolean>checkUserAccountDuplicate(@RequestParam(value = "account") String account) {
		
		UserEntity result = userService.findByAccount(account);
		
		return new JsonResult<Boolean>(HttpState.SUCCESS.getState(), result != null ? true : false);
	}

}
