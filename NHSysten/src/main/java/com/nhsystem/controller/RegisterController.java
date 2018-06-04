package com.nhsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhsystem.entities.User;
import com.nhsystem.services.UserService;

@Controller
public class RegisterController {
	
	UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/register")
	public String registerForm(Model model) {
	
		model.addAttribute("user", new User());
		return "views/registerForm";
	}
	
	@PostMapping("/reg")
		public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
			if (bindingResult.hasErrors()){
				return "views/registerForm";
			}
		
		if(userService.isUserPresent(user.getEmail()))	{
			model.addAttribute("exist",true);
			return "views/registerForm";
		}
		userService.creatUser(user);
			
		return "views/success";
		}
	

}
