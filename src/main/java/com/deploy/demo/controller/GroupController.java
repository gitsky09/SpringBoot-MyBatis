package com.deploy.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.deploy.demo.entity.GroupEntity;
import com.deploy.demo.service.GroupService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GroupController {

	final GroupService groupService;

	@PostMapping("/group")
	String insertGroup(@ModelAttribute GroupEntity group,Model model) {
		model.addAttribute("groupobject", group);
		groupService.insertGroup(group);
		return "redirect:group";
	}

	@GetMapping("/group")
	public String listGroup(Model model) {
		Iterable<GroupEntity> groupList = groupService.listGroup();
		model.addAttribute("grouplist",groupList);
		
		GroupEntity groupObject = new GroupEntity();
		model.addAttribute("groupobject",groupObject);
		return "group";
	}
	
	@GetMapping("/group/")
	GroupEntity getGroup(int id) {
		return groupService.getGroup(id);
	}

	@PutMapping("/group")
	Integer updateGroup(GroupEntity group) {
		return groupService.updateGroup(group);
	}
	
	@DeleteMapping("/group/")
	void deleteGroup(int id) {
		groupService.deleteGroup(id);
	}
	
}
