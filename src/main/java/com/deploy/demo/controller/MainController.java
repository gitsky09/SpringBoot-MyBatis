package com.deploy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.deploy.demo.core.dto.Permission;
//import com.deploy.demo.security.CustomAuthenticationProvider;
import com.deploy.demo.service.GroupService;
import com.deploy.demo.service.UserService;
import com.deploy.demo.service.AuthorityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {

	final UserService userService;
	
	final AuthorityService authorityService;
	
	final GroupService groupService;
	
//	final CustomAuthenticationProvider loginService;
	
	/**
	 * 首頁
	 */
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	/**
	 * [登入後主頁]
	 * @return 分配到main
	 */
	@GetMapping("/main")
	public String loadMainPage() {
		return "main";
	}
	
//	@GetMapping("/main")
//	String listSearchUser(Model model) {
//		Iterable<UserEntity> userList = userService.listUser();
//		model.addAttribute("userList", userList);
		
//		UserEntity userObject = new UserEntity();
//		model.addAttribute("userObject", userObject);
//		return "main";
//	}
	
	/**
	 * [自訂登入頁]
	 * @return 分配到login
	 */
	@GetMapping("/userlogin")
	public String login(@ModelAttribute Permission user) {
		return "login";
	}
	
	@PostMapping("/userlogin")
	public String successLogin(@ModelAttribute Permission user) {
	    return "main";
	}
	
	/**
	 * [登出自訂頁面]
	 * @return 分配到information
	 */
	@GetMapping("/information")
	public String logout() {
		return "information";
	}
	
	// Login form with error
	  @GetMapping("/login-error")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "login";
	  }
	
	/**
	 * [棄用 跳轉結構]
	 * @return
	 */
//	@PostMapping("/main")
//	String menuL() {
//		
//		char control = 0;
//		
//		switch(control){
//			case 'b':
//				return "business";
//			case 'u':
//				return "user";
//			case 'g':
//				return "group";
//			case 'a':
//				return "authority";
//			default :
//				return "頁面錯誤";
//			
//		}
//				
//	}
}
