package com.deploy.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.deploy.demo.entity.AuthorityEntity;
import com.deploy.demo.service.AuthorityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthorirtyController {

	final AuthorityService authorityService;

	@PostMapping("/authority")
	String insertAuthority(@ModelAttribute AuthorityEntity authority,Model model) {
	model.addAttribute("authorityobject", authority);
	authorityService.insertAuthority(authority);
	return "redirect:authority";
}

	@GetMapping("/authority")
	public String listAuthority(Model model) {
		Iterable<AuthorityEntity> authorityList = authorityService.listAuthority();
		model.addAttribute("authoritylist",authorityList);
		
		AuthorityEntity authorityObject = new AuthorityEntity();
		model.addAttribute("authorityobject", authorityObject);
		return"authority";
	}

	@GetMapping("/authority/")
	AuthorityEntity getAuthority(int id) {
		return authorityService.getAuthority(id);
	}

	@PutMapping("/authority")
	Integer updateAuthority(AuthorityEntity authority) {
		return authorityService.updateAuthority(authority);
	}

	@DeleteMapping("/authority")
	void deleteAuthority(int id) {
		authorityService.deleteAuthority(id);
	}
	
}
