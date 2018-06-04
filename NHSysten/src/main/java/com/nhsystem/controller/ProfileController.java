package com.nhsystem.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nhsystem.entities.User;
import com.nhsystem.services.TaskService;
import com.nhsystem.services.UserService;

@Controller
public class ProfileController {
	
	TaskService taskService;
	UserService userService;
	
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/profile")
	public String showProfilePage(Model model, Principal principal) {
		
		String email = principal.getName();
		User user = userService.findOneUser(email);
		
		model.addAttribute("tasks",taskService.findUserTask(user));
		
		return "views/profile";
	}
	
	

}
