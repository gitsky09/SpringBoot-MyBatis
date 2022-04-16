package com.deploy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.deploy.demo.entity.UserEntity;
import com.deploy.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ViewController {

	@Autowired
	final UserService viewService;
	
	@GetMapping("/example")
	public String getUser(Model model) {
		Iterable<UserEntity> userList = viewService.listUser();
		model.addAttribute("userlist", userList);
		UserEntity user = new UserEntity();
		model.addAttribute("userobject", user);
		return "view";
	}
	
}
