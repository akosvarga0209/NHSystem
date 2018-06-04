package com.nhsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhsystem.services.UserService;

@Controller
public class UserController {
	
	UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
		public String listUsers(Model model, @RequestParam(defaultValue="") String name) {
			model.addAttribute("users",userService.findByName(name));
			return "views/list";
	}
	
	

}
