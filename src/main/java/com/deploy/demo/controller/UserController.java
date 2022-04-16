package com.deploy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.deploy.demo.entity.UserEntity;
import com.deploy.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	final UserService userService;
	
	@PostMapping("/user")
	public String insertUser(@ModelAttribute UserEntity user,Model model) {
		model.addAttribute("userobject", model);
		userService.save(user);
		return "redirect:user";
		
	}
	
	@GetMapping("/user")
	public String listUser(Model model) {
		Iterable<UserEntity> userList = userService.listUser();
		model.addAttribute("userlist",userList);
		
		UserEntity userObject = new UserEntity();
		model.addAttribute("userObject", userObject);
		return "user";
	}
	
	@GetMapping("/user/")
	UserEntity getUser(int id) {
		return userService.getUser(id);
	}
	
	@PutMapping("/user")
	Integer updateUser(UserEntity user) {
		return userService.updateUser(user);
	}
	
	@GetMapping("/login")
	public String login(@ModelAttribute UserEntity user) {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute UserEntity userVo) {
		return "register";
	}
	
}
