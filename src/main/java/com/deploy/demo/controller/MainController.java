package com.deploy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.deploy.demo.entity.UserEntity;
import com.deploy.demo.service.AuthorityService;
import com.deploy.demo.service.GroupService;
import com.deploy.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {

	final UserService userService;
	
	final AuthorityService authorityService;
	
	final GroupService groupService;
	
	@GetMapping("/main")
	String listSearchUser(Model model) {
		Iterable<UserEntity> userList = userService.listUser();
		model.addAttribute("userList", userList);
		
		UserEntity userObject = new UserEntity();
		model.addAttribute("userObject", userObject);
		return "main";
	}
	
	@PostMapping("/main")
	String menuL() {
		
		char control = 0;
		
		switch(control){
			case 'b':
				return "business";
			case 'u':
				return "user";
			case 'g':
				return "group";
			case 'a':
				return "authority";
			default :
				return "頁面錯誤";
			
		}
				
	}
}
